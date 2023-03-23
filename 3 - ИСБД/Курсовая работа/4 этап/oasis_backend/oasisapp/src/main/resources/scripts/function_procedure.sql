--Процедура для входа в систему за пользователя
CREATE OR REPLACE PROCEDURE loginAsUser(arg_login varchar(10))
AS
$$
BEGIN

    IF (SELECT login FROM users WHERE users.login = arg_login) IS NOT NULL
    THEN
        UPDATE users
        SET last_login_date = current_date,
            status='В сети'
        WHERE users.login = arg_login;
    ELSE
        RAISE EXCEPTION 'Данный пользователь не зарегистрирован';
    END IF;
END
$$ LANGUAGE plpgsql;

--Процедура для выхода пользователя из системы
CREATE OR REPLACE PROCEDURE logoutFromUser(arg_login varchar(10))
AS
$$
BEGIN

    IF (SELECT login FROM users WHERE users.login = arg_login) IS NOT NULL
    THEN
        UPDATE users
        SET status='Не в сети'
        WHERE users.login = arg_login;
    ELSE
        RAISE EXCEPTION 'Данный пользователь не зарегистрирован';
    END IF;
END ;
$$ LANGUAGE plpgsql;


--Триггерная-функция для проверки на уникальность записи в игре
CREATE OR REPLACE FUNCTION checkGameOnUniqueFunction() RETURNS TRIGGER
AS
$$
BEGIN
    IF (SELECT COUNT(*)
        FROM games
        WHERE games.name = NEW.name
        GROUP BY games.name) >= 1
    THEN
        RAISE EXCEPTION 'Данная игра уже опубликована!';
    END IF;
    RETURN NEW;
END
$$ LANGUAGE plpgsql;


--Триггерная-функция для проверки на уникальность записи в магазине
CREATE OR REPLACE FUNCTION checkShopOnUniqueFunction() RETURNS TRIGGER
AS
$$
BEGIN
    IF (SELECT COUNT(*)
        FROM shop
        WHERE shop.game_id = NEW.game_id
        GROUP BY shop.game_id) >= 1
    THEN
        RAISE EXCEPTION 'Запись об игре % уже существует в таблице Магазин', getGameNameById(NEW.game_id);
    END IF;
    RETURN NEW;
END
$$ LANGUAGE plpgsql;



--Функция для получения названия игры по ее ID
CREATE OR REPLACE FUNCTION getGameNameById(id_arg integer) RETURNS TEXT
AS
$$
BEGIN
    RETURN (SELECT name FROM games WHERE id_arg = games.id);
END;
$$ LANGUAGE plpgsql;


--Функция для пополнения баланса кошелька
CREATE OR REPLACE PROCEDURE replenishbalance(arg_login varchar(10), arg_balance double precision)
AS
$$
DECLARE
    wallet_id_to_change integer;
BEGIN

    IF (SELECT users.login FROM users WHERE users.login = arg_login) IS NOT NULL
    THEN
        wallet_id_to_change = (SELECT users.wallet_id FROM users WHERE users.login = arg_login);
        UPDATE wallets
        SET balance= ((SELECT balance
                       FROM wallets
                       WHERE wallets.id = wallet_id_to_change) + arg_balance)
        WHERE wallets.id = wallet_id_to_change;

        INSERT INTO transactions(user_login, transaction_type, sum, transaction_date)
        VALUES (arg_login, 'Пополнение баланса', arg_balance, current_timestamp);
    ELSE
        RAISE EXCEPTION 'Данный пользователь не зарегистрирован';
    END IF;
END;
$$ LANGUAGE plpgsql;


--Триггерная-функция для проверки на уникальность записи в библиотеке + проверку на средства
CREATE OR REPLACE FUNCTION checkLibraryOnUniqueFunction() RETURNS TRIGGER
AS
$$
DECLARE
    стоимость_игры real;
BEGIN
    IF (SELECT COUNT(*)
        FROM library
        WHERE library.user_login = NEW.user_login
          AND library.game_id = NEW.game_id
        GROUP BY library.user_login, library.game_id) >= 1
    THEN
        RAISE EXCEPTION 'У вас уже приобретена эта игра!';
    ELSE
        стоимость_игры = (SELECT shop.price FROM shop WHERE shop.game_id = NEW.game_id);

        IF (SELECT wallets.balance
            FROM wallets
            WHERE wallets.id IN (SELECT users.wallet_id FROM users WHERE users.login = NEW.user_login)) < стоимость_игры
        THEN
            RAISE EXCEPTION 'У вас не хватает средств для покупки игры!';

        ELSE
            INSERT INTO transactions(user_login, transaction_type, sum, transaction_date)
            VALUES (NEW.user_login, 'Покупка игры', стоимость_игры, current_timestamp);

            UPDATE wallets
            SET balance=(SELECT wallets.balance
                         FROM wallets
                         WHERE wallets.id IN (SELECT users.wallet_id FROM users WHERE users.login = NEW.user_login)) -
                        стоимость_игры
            WHERE wallets.id IN (SELECT users.wallet_id FROM users WHERE users.login = NEW.user_login);
        END IF;


    END IF;
    RETURN NEW;
END
$$ LANGUAGE plpgsql;



--Процедура для запуска игры из библиотеки пользователя
CREATE OR REPLACE PROCEDURE enterTheGame(arg_login varchar(10), game_name varchar(64))
AS
$$
BEGIN

    IF (SELECT library.id
        FROM library
        WHERE library.user_login = arg_login
          AND library.game_id IN (SELECT games.id FROM games WHERE games.name = game_name)) IS NOT NULL
    THEN
        UPDATE library
        SET last_run_date = current_timestamp
        WHERE library.user_login = arg_login
          AND library.game_id IN (SELECT games.id FROM games WHERE games.name = game_name);
    ELSE
        RAISE EXCEPTION 'Данный пользователь/игра не существует';
    END IF;
END
$$ LANGUAGE plpgsql;


--Триггерная-функция для проверки на уникальность записи в инвентаре
CREATE OR REPLACE FUNCTION checkInventoryOnUniqueFunction() RETURNS TRIGGER
AS
$$
BEGIN
    IF (SELECT COUNT(*)
        FROM inventory
        WHERE inventory.user_login = NEW.user_login
          AND inventory.item_id = NEW.item_id
        GROUP BY inventory.user_login, inventory.item_id) > 1
    THEN
        DELETE
        FROM inventory
        WHERE inventory.id = NEW.id;
    END IF;

    UPDATE inventory
    SET amount = (SELECT amount
                  FROM inventory
                  WHERE inventory.user_login = NEW.user_login
                    AND inventory.item_id = NEW.item_id) + 1
    WHERE item_id = NEW.item_id
      AND user_login = NEW.user_login;
    RETURN NEW;
END;
$$ LANGUAGE plpgsql;

--Триггерная функция для проверки может ли пользователь продать вещь, а также удаления/изменение записей в Инвентаре пользователя
CREATE OR REPLACE FUNCTION sellItemOnMarket() RETURNS TRIGGER
AS
$$
DECLARE
    количество_вещей integer := 0;
BEGIN
    IF (SELECT inventory.id
        FROM inventory
        WHERE inventory.item_id = NEW.item_id
          AND inventory.user_login = NEW.user_login) IS NULL
    THEN
        DELETE
        FROM market
        WHERE market.id = NEW.id;

        RAISE EXCEPTION 'Пользователь не может продать вещь, т.к не владеет ею';

    ELSE
        количество_вещей = (SELECT inventory.amount
                            FROM inventory
                            WHERE inventory.item_id = NEW.item_id
                              AND inventory.user_login = NEW.user_login);
        IF (количество_вещей > 1)
        THEN
            количество_вещей = количество_вещей - 1;

            UPDATE inventory
            SET amount=количество_вещей
            WHERE item_id = NEW.item_id
              AND user_login = NEW.user_login;
        ELSE
            DELETE
            FROM inventory
            WHERE inventory.item_id = NEW.item_id
              AND inventory.user_login = NEW.user_login;

        END IF;
    END IF;
    RETURN NEW;
END ;
$$ LANGUAGE plpgsql;


--Функция для пополнения баланса кошелька на сумму проданной вещи
CREATE OR REPLACE PROCEDURE replenishbalanceForSoldItem(arg_login varchar(10), arg_balance double precision)
AS
$$
DECLARE
    wallet_id_to_change integer;
BEGIN

    IF (SELECT users.login FROM users WHERE users.login = arg_login) IS NOT NULL
    THEN
        wallet_id_to_change = (SELECT users.wallet_id FROM users WHERE users.login = arg_login);
        UPDATE wallets
        SET balance= ((SELECT balance
                       FROM wallets
                       WHERE wallets.id = wallet_id_to_change) + arg_balance)
        WHERE wallets.id = wallet_id_to_change;

        INSERT INTO transactions(user_login, transaction_type, sum, transaction_date)
        VALUES (arg_login, 'Продажа вещи', arg_balance, current_timestamp);
    ELSE
        RAISE EXCEPTION 'Данный пользователь не зарегистрирован';
    END IF;
END;
$$ LANGUAGE plpgsql;


--Функция для списания баланса кошелька на сумму купленной вещи
CREATE OR REPLACE PROCEDURE chargebalanceForSoldItem(arg_login varchar(10), arg_balance double precision)
AS
$$
DECLARE
    wallet_id_to_change integer;
BEGIN

    IF (SELECT users.login FROM users WHERE users.login = arg_login) IS NOT NULL
    THEN
        wallet_id_to_change = (SELECT users.wallet_id FROM users WHERE users.login = arg_login);
        UPDATE wallets
        SET balance= ((SELECT balance
                       FROM wallets
                       WHERE wallets.id = wallet_id_to_change) - arg_balance)
        WHERE wallets.id = wallet_id_to_change;

        INSERT INTO transactions(user_login, transaction_type, sum, transaction_date)
        VALUES (arg_login, 'Покупка вещи', arg_balance, current_timestamp);
    ELSE
        RAISE EXCEPTION 'Данный пользователь не зарегистрирован';
    END IF;
END;
$$ LANGUAGE plpgsql;




