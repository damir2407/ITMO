--Триггерная функция для проверки может ли пользователь продать вещь, а также удаления/изменение записей в Инвентаре пользователя
CREATE OR REPLACE FUNCTION "ПродажаНаТорговойПлощадке"() RETURNS TRIGGER
AS
$$
DECLARE
    количество_вещей integer := 0;
BEGIN
    IF (SELECT ID
        FROM "Инвентарь"
        WHERE "Внутриигровая_вещь ID" = NEW."Внутриигровая_вещь ID"
          AND "Логин_Пользователя" = NEW."Логин_Пользователя") IS NULL
    THEN
        DELETE
        FROM "Торговая площадка"
        WHERE "Торговая площадка".ID = NEW.ID;

        RAISE EXCEPTION 'Пользователь % не может продать вещь %, т.к не владеет ею', NEW."Логин_Пользователя", "ПолучитьНазваниеВещиПоID"(NEW."Внутриигровая_вещь ID");

    ELSE
        количество_вещей = (SELECT "Количество"
                            FROM "Инвентарь"
                            WHERE "Внутриигровая_вещь ID" = NEW."Внутриигровая_вещь ID"
                              AND "Логин_Пользователя" = NEW."Логин_Пользователя");
        IF (количество_вещей > 1)
        THEN
            количество_вещей = количество_вещей - 1;

            UPDATE "Инвентарь"
            SET "Количество"=количество_вещей
            WHERE "Внутриигровая_вещь ID" = NEW."Внутриигровая_вещь ID"
              AND "Логин_Пользователя" = NEW."Логин_Пользователя";
        ELSE
            DELETE
            FROM "Инвентарь"
            WHERE "Внутриигровая_вещь ID" = NEW."Внутриигровая_вещь ID"
              AND "Логин_Пользователя" = NEW."Логин_Пользователя";

        END IF;
    END IF;
    RETURN NEW;
END;
$$ LANGUAGE plpgsql;


CREATE OR REPLACE FUNCTION "ПолучитьНазваниеВещиПоID"(id_arg integer) RETURNS VARCHAR(128)
AS
$$
BEGIN
    RETURN (SELECT "Название" FROM "Внутриигровая вещь" WHERE id_arg = "Внутриигровая вещь".id);
END;
$$ LANGUAGE plpgsql;




--Триггерная функция для проверки на наличие игры у пользователя в библиотеке в магазине и проверка на уникальность
CREATE OR REPLACE FUNCTION "ПроверитьНаНаличиеВМагазине"() RETURNS TRIGGER
AS
$$
BEGIN
    IF (SELECT "Магазин".id FROM "Магазин" WHERE "Магазин"."Игра_ID" = NEW."Игра_ID") is NULL
    THEN
        DELETE
        FROM "Библиотека"
        WHERE "Библиотека".ID = NEW.ID;
        RAISE EXCEPTION 'Пользователь % не может иметь игру %, т.к она отсутствует в магазине', NEW."Логин_Пользователя", "ПолучитьНазваниеИгрыПоID"(NEW."Игра_ID");
    END IF;

    IF ("КоличествоОдинаковыхВБиблиотеке"(NEW."Игра_ID", NEW."Логин_Пользователя")) > 1
    THEN
        DELETE
        FROM "Библиотека"
        WHERE "Библиотека".ID = NEW.ID;
        RAISE EXCEPTION 'Пользователь % не может добавить игру %, т.к она уже есть в его библиотеке', NEW."Логин_Пользователя", "ПолучитьНазваниеИгрыПоID"(NEW."Игра_ID");
    END IF;

    CALL "КупитьИгру"(NEW."Логин_Пользователя", NEW."Игра_ID");

    RETURN NEW;
END;
$$ LANGUAGE plpgsql;


--Функция для подсчета количества одинаковых строк в библиотеке
CREATE OR REPLACE FUNCTION "КоличествоОдинаковыхВБиблиотеке"(arg_game_id integer, arg_login varchar(10)) RETURNS INTEGER
AS
$$
BEGIN
    RETURN (SELECT COUNT(*)
            FROM "Библиотека"
            WHERE "Библиотека"."Логин_Пользователя" = arg_login
              AND "Библиотека"."Игра_ID" = arg_game_id
            GROUP BY "Библиотека"."Логин_Пользователя", "Библиотека"."Игра_ID");
END;
$$ LANGUAGE plpgsql;


--Функция для получения названия игры по ее ID
CREATE OR REPLACE FUNCTION "ПолучитьНазваниеИгрыПоID"(id_arg integer) RETURNS VARCHAR(128)
AS
$$
BEGIN
    RETURN (SELECT "Название" FROM "Игра" WHERE id_arg = "Игра".id);
END;
$$ LANGUAGE plpgsql;



--Триггерная функция для проверки может ли пользователь опубликовать обзор на игру(есть ли она у него в библиотеке)
CREATE OR REPLACE FUNCTION "ПроверитьРуководствоВБиблиотеке"() RETURNS TRIGGER
AS
$$
BEGIN
    IF (SELECT "Библиотека".ID
        FROM "Библиотека"
        WHERE "Библиотека"."Логин_Пользователя" = NEW."Логин_Пользователя"
          AND "Библиотека"."Игра_ID" = NEW."Игра_ID") IS NULL
    THEN
        DELETE
        FROM "Руководство"
        WHERE "Руководство".ID = NEW.ID;
        RAISE EXCEPTION 'Пользователь % не может оставить обзор на игру %, т.к он не имеет ее в библиотеке', NEW."Логин_Пользователя", "ПолучитьНазваниеИгрыПоID"(NEW."Игра_ID");
    END IF;
    RETURN NEW;
END;
$$ LANGUAGE plpgsql;

--Триггерная функция для проверки может ли пользователь опубликовать мод на игру(есть ли она у него в библиотеке)
CREATE OR REPLACE FUNCTION "ПроверитьМастерскуюВБиблиотеке"() RETURNS TRIGGER
AS
$$
BEGIN
    IF (SELECT "Библиотека".ID
        FROM "Библиотека"
        WHERE "Библиотека"."Логин_Пользователя" = NEW."Логин_Пользователя"
          AND "Библиотека"."Игра_ID" = NEW."Игра_ID") IS NULL
    THEN
        DELETE
        FROM "Мастерская"
        WHERE "Мастерская".ID = NEW.ID;
        RAISE EXCEPTION 'Пользователь % не может опубликовывать моды на игру %, т.к он не имеет ее в библиотеке', NEW."Логин_Пользователя", "ПолучитьНазваниеИгрыПоID"(NEW."Игра_ID");
    END IF;
    RETURN NEW;
END;
$$ LANGUAGE plpgsql;


--Триггерная функция для создания пустого кошелька пользователю
CREATE OR REPLACE FUNCTION "СоздатьПустойКошелек"() RETURNS TRIGGER
AS
$$
BEGIN
    INSERT INTO "Кошелек"("Логин_Пользователя", "Баланс") VALUES (NEW."Логин", 0);
    RETURN NEW;
END;
$$ LANGUAGE plpgsql;


--Триггерная функция для установления информации в группу и участников группы
CREATE OR REPLACE FUNCTION "ПроверитьГруппу"() RETURNS TRIGGER
AS
$$
BEGIN
    UPDATE "Группа"
    SET "Количество участников" = 1
    WHERE "id" = NEW."id";

    INSERT INTO "Участники Группы"("Группа_ID", "Логин_Пользователя") VALUES (NEW."id", NEW."Логин_Создателя");
    RETURN NEW;
END;
$$ LANGUAGE plpgsql;



--Триггерная функция для проверки может ли пользователь оставить сообщение в группу
CREATE OR REPLACE FUNCTION "ПроверитьСообщенияНаГруппу"() RETURNS TRIGGER
AS
$$
BEGIN
    IF (SELECT "Группа_ID"
        FROM "Участники Группы"
        WHERE "Группа_ID" = NEW."Группа_ID"
          AND "Логин_Пользователя" = NEW."Логин_Пользователя") IS NULL
    THEN
        DELETE
        FROM "Список сообщений"
        WHERE "Список сообщений"."id" = NEW."id";
        RAISE EXCEPTION 'Пользователь % не может оставлять сообщение в группе %, т.к он не состоит в ней', NEW."Логин_Пользователя", "ПолучитьНазваниеГруппыПоID"(NEW."Группа_ID");
    END IF;

    RETURN NEW;
END;
$$ LANGUAGE plpgsql;


CREATE OR REPLACE FUNCTION "ПолучитьНазваниеГруппыПоID"(id_arg integer) RETURNS VARCHAR(64)
AS
$$
BEGIN
    RETURN (SELECT "Название" FROM "Группа" WHERE id_arg = "Группа".id);
END;
$$ LANGUAGE plpgsql;


--Триггерная-функция для проверки на уникальность записи в инвентаре
CREATE OR REPLACE FUNCTION "ПроверитьИнвентарь"() RETURNS TRIGGER
AS
$$
BEGIN
    IF (SELECT COUNT(*)
        FROM "Инвентарь"
        WHERE "Инвентарь"."Логин_Пользователя" = NEW."Логин_Пользователя"
          AND "Инвентарь"."Внутриигровая_вещь ID" = NEW."Внутриигровая_вещь ID"
        GROUP BY "Инвентарь"."Логин_Пользователя", "Инвентарь"."Внутриигровая_вещь ID") > 1
    THEN
        DELETE
        FROM "Инвентарь"
        WHERE "Инвентарь".ID = NEW.ID;
    END IF;

    UPDATE "Инвентарь"
    SET "Количество" = (SELECT "Количество"
                        FROM "Инвентарь"
                        WHERE "Логин_Пользователя" = NEW."Логин_Пользователя"
                          AND "Внутриигровая_вещь ID" = NEW."Внутриигровая_вещь ID") + 1
    WHERE "Внутриигровая_вещь ID" = NEW."Внутриигровая_вещь ID"
      AND "Логин_Пользователя" = NEW."Логин_Пользователя";
    RETURN NEW;
END;
$$ LANGUAGE plpgsql;



--Триггерная-функция для проверки на уникальность записи в игре
CREATE OR REPLACE FUNCTION "ПроверитьНаУникальностьИгру"() RETURNS TRIGGER
AS
$$
BEGIN
    IF (SELECT COUNT(*)
        FROM "Игра"
        WHERE "Игра"."Название" = NEW."Название"
        GROUP BY "Игра"."Название") > 1
    THEN
        DELETE
        FROM "Игра"
        WHERE "Игра".ID = NEW.ID;
    END IF;
    RETURN NEW;
END ;
$$ LANGUAGE plpgsql;


--Триггерная-функция для проверки на уникальность записи в магазине
CREATE OR REPLACE FUNCTION "ПроверитьНаУникальностьМагазин"() RETURNS TRIGGER
AS
$$
BEGIN
    IF (SELECT COUNT(*)
        FROM "Магазин"
        WHERE "Магазин"."Игра_ID" = NEW."Игра_ID"
        GROUP BY "Магазин"."Игра_ID") > 1
    THEN
        DELETE
        FROM "Магазин"
        WHERE "Магазин".ID = NEW.ID;
        RAISE EXCEPTION 'Запись об игре % уже существует в таблице Магазин', "ПолучитьНазваниеИгрыПоID"(NEW."Игра_ID");
    END IF;
    RETURN NEW;
END ;
$$ LANGUAGE plpgsql;


--Триггерная-функция для проверки на уникальность записи в кошельке
CREATE OR REPLACE FUNCTION "ПроверитьНаУникальностьКошелек"() RETURNS TRIGGER
AS
$$
BEGIN
    IF (SELECT COUNT(*)
        FROM "Кошелек"
        WHERE "Кошелек"."Логин_Пользователя" = NEW."Логин_Пользователя"
        GROUP BY "Кошелек"."Логин_Пользователя") > 1
    THEN
        DELETE
        FROM "Кошелек"
        WHERE "Кошелек".ID = NEW.ID;
        RAISE EXCEPTION 'Запись о кошельке пользователя % уже существует в таблице Кошелек', NEW."Логин_Пользователя";
    END IF;
    RETURN NEW;
END ;
$$ LANGUAGE plpgsql;



--BUSINESS PROCEDURES--------------------------------


--процедура для входа в систему за разработчика
CREATE OR REPLACE PROCEDURE "ВойтиЗаРазработчика"(arg_login varchar(10))
AS
$$
BEGIN


    IF (SELECT "Логин" FROM "Разработчик" WHERE "Разработчик"."Логин" = arg_login) IS NOT NULL
    THEN
        UPDATE "Разработчик"
        SET "Дата последнего входа" = current_date,
            "Статус"='В сети'
        WHERE "Разработчик"."Логин" = arg_login;
    ELSE
        RAISE EXCEPTION 'Данный разработчик не зарегистрирован';
    END IF;
END;
$$ LANGUAGE plpgsql;


--процедура для выхода разработчика из системы
CREATE OR REPLACE PROCEDURE "ВыйтиИзРазработчика"(arg_login varchar(10))
AS
$$
BEGIN

    IF (SELECT "Логин" FROM "Разработчик" WHERE "Разработчик"."Логин" = arg_login) IS NOT NULL
    THEN
        UPDATE "Разработчик"
        SET "Статус"='Не в сети'
        WHERE "Разработчик"."Логин" = arg_login;
    ELSE
        RAISE EXCEPTION 'Данный разработчик не зарегистрирован';
    END IF;
END;
$$ LANGUAGE plpgsql;


--процедура для входа в систему за пользователя
CREATE OR REPLACE PROCEDURE "ВойтиЗаПользователя"(arg_login varchar(10))
AS
$$
BEGIN

    IF (SELECT "Логин" FROM "Пользователь" WHERE "Пользователь"."Логин" = arg_login) IS NOT NULL
    THEN
        UPDATE "Пользователь"
        SET "Дата последнего входа" = current_date,
            "Статус"='В сети'
        WHERE "Пользователь"."Логин" = arg_login;
    ELSE
        RAISE EXCEPTION 'Данный пользователь не зарегистрирован';
    END IF;
END;
$$ LANGUAGE plpgsql;


--процедура для выхода пользователя из системы
CREATE OR REPLACE PROCEDURE "ВыйтиИзПользователя"(arg_login varchar(10))
AS
$$
BEGIN

    IF (SELECT "Логин" FROM "Пользователь" WHERE "Пользователь"."Логин" = arg_login) IS NOT NULL
    THEN
        UPDATE "Пользователь"
        SET "Статус"='Не в сети'
        WHERE "Пользователь"."Логин" = arg_login;
    ELSE
        RAISE EXCEPTION 'Данный пользователь не зарегистрирован';
    END IF;
END;
$$ LANGUAGE plpgsql;

--процедура для пополнения баланса кошелька
CREATE OR REPLACE PROCEDURE "ПополнитьБаланс"(arg_login varchar(10), arg_balance real)
AS
$$
BEGIN

    IF (SELECT "Логин" FROM "Пользователь" WHERE "Пользователь"."Логин" = arg_login) IS NOT NULL
    THEN
        UPDATE "Кошелек"
        SET "Баланс"= ((SELECT "Баланс"
                        FROM "Кошелек"
                        WHERE "Логин_Пользователя" = arg_login) + arg_balance)
        WHERE "Кошелек"."Логин_Пользователя" = arg_login;

        INSERT INTO "Транзакции"("Логин_Пользователя", "Вид транзакции", "Сумма", "Дата транзакции")
        VALUES (arg_login, 'Пополнение баланса', arg_balance, current_timestamp);
    ELSE
        RAISE EXCEPTION 'Данный пользователь не зарегистрирован';
    END IF;
END;
$$ LANGUAGE plpgsql;


--процедура для запуска игры из библиотеки пользователя
CREATE OR REPLACE PROCEDURE "ВойтиВИгруИзБиблиотеки"(arg_login varchar(10), arg_game integer)
AS
$$
BEGIN

    IF (SELECT "Библиотека".id
        FROM "Библиотека"
        WHERE "Библиотека"."Логин_Пользователя" = arg_login
          AND "Библиотека"."Игра_ID" = arg_game) IS NOT NULL
    THEN
        UPDATE "Библиотека"
        SET "Дата последнего запуска" = current_date
        WHERE "Библиотека"."Логин_Пользователя" = arg_login
          AND "Библиотека"."Игра_ID" = arg_game;
    ELSE
        RAISE EXCEPTION 'Данный пользователь/игра не существует';
    END IF;
END;
$$ LANGUAGE plpgsql;



--процедура для присоединения к какой-либо группе
CREATE OR REPLACE PROCEDURE "ВступитьВГруппу"(arg_group integer, arg_login varchar(10))
AS
$$
BEGIN

    IF (SELECT "Пользователь"."Логин"
        FROM "Пользователь"
        WHERE "Пользователь"."Логин" = arg_login is NULL) || (SELECT "Группа".id
                                                              FROM "Группа"
                                                              WHERE "Группа"."id" = arg_group IS NULL)
    THEN
        RAISE EXCEPTION 'Данный пользователь/группа не существует';
    ELSE
        INSERT INTO "Участники Группы"("Группа_ID", "Логин_Пользователя") VALUES (arg_group, arg_login);

        UPDATE "Группа"
        SET "Количество участников" = (SELECT "Количество участников"
                                       FROM "Группа"
                                       WHERE "Группа".id = arg_group) + 1
        WHERE "Группа".id = arg_group;
    END IF;
END;
$$ LANGUAGE plpgsql;




--процедура для выхода из группы
CREATE OR REPLACE PROCEDURE "ВыйтиИзГруппы"(arg_group integer, arg_login varchar(10))
AS
$$
BEGIN


    IF (SELECT "Группа_ID"
        FROM "Участники Группы"
        WHERE "Группа_ID" = arg_group AND "Логин_Пользователя" = arg_login) is NULL
    THEN
         RAISE EXCEPTION 'Данный пользователь не состоит в заданной группе!';
    END IF;

    IF (SELECT "Пользователь"."Логин"
        FROM "Пользователь"
        WHERE "Пользователь"."Логин" = arg_login is NULL) || (SELECT "Группа".id
                                                              FROM "Группа"
                                                              WHERE "Группа"."id" = arg_group IS NULL)
    THEN
        RAISE EXCEPTION 'Данный пользователь/группа не существует';
    ELSE
        --если создатель хочет выйти из группы, то удалить группу
        IF (SELECT "Логин_Создателя" FROM "Группа" WHERE "Группа".id = arg_group) = arg_login
        THEN
            DELETE
            FROM "Группа"
            WHERE "Группа".id = arg_group;
        ELSE

            DELETE
            FROM "Участники Группы"
            WHERE "Группа_ID" = arg_group
              AND "Логин_Пользователя" = arg_login;

            UPDATE "Группа"
            SET "Количество участников" = (SELECT "Количество участников"
                                           FROM "Группа"
                                           WHERE "Группа".id = arg_group) - 1
            WHERE "Группа".
                      id = arg_group;


        END IF;

    END IF;
END;
$$ LANGUAGE plpgsql;


--процедура для покупки игры в магазине
CREATE OR REPLACE PROCEDURE "КупитьИгру"(arg_login varchar(10), arg_game integer)
AS
$$

DECLARE
    стоимость_игры real;
BEGIN

    стоимость_игры = (SELECT "Стоимость" FROM "Магазин" WHERE "Игра_ID" = arg_game);

    IF (SELECT "Баланс"
        FROM "Кошелек"
        WHERE "Логин_Пользователя" = arg_login) < стоимость_игры
    THEN
        RAISE EXCEPTION 'У пользователя % не хватает денег для покупки игры %',arg_login,"ПолучитьНазваниеИгрыПоID"(arg_game);

    ELSE
        INSERT INTO "Транзакции"("Логин_Пользователя", "Вид транзакции", "Сумма", "Дата транзакции")
        VALUES (arg_login, 'Покупка Игры', стоимость_игры, current_timestamp);

        UPDATE "Кошелек"
        SET "Баланс"=(SELECT "Баланс"
                      FROM "Кошелек"
                      WHERE "Логин_Пользователя" = arg_login) - стоимость_игры
        WHERE "Логин_Пользователя" = arg_login;
    END IF;
END;

$$ LANGUAGE plpgsql;


--процедура для покупки вещи на торговой площадке
CREATE OR REPLACE PROCEDURE "КупитьВещьНаТорговойПлощадке"(arg_login_buyer varchar(10), arg_torgovaya_ploshadka integer)
AS
$$

DECLARE
    стоимость_вещи real;
    логин_продавца varchar(10);
    вещь_с_лота    integer;
BEGIN

    IF (SELECT id
        FROM "Торговая площадка"
        WHERE "Торговая площадка".id = arg_torgovaya_ploshadka) IS NULL
    THEN
        RAISE EXCEPTION 'На Торговой Площадке отсутствует заданный лот!';

    END IF;

    IF (SELECT "Логин"
        FROM "Пользователь"
        WHERE "Пользователь"."Логин" = arg_login_buyer) IS NULL
    THEN
        RAISE EXCEPTION 'Пользователь % не существует', arg_login_buyer;
    END IF;

    IF (SELECT "Логин_Пользователя"
        FROM "Торговая площадка"
        WHERE "Торговая площадка".id = arg_torgovaya_ploshadka) = arg_login_buyer
    THEN
        RAISE EXCEPTION 'Вы не можете купить свою же вещь!';
    end if;

    стоимость_вещи =
            (SELECT "Стоимость"
             FROM "Торговая площадка"
             WHERE "Торговая площадка".id = arg_torgovaya_ploshadka);


    логин_продавца =
            (SELECT "Логин_Пользователя"
             FROM "Торговая площадка"
             WHERE "Торговая площадка".id = arg_torgovaya_ploshadka);

    вещь_с_лота = (SELECT "Внутриигровая_вещь ID"
                   FROM "Торговая площадка"
                   WHERE "Торговая площадка".id = arg_torgovaya_ploshadka);


    IF (SELECT "Баланс"
        FROM "Кошелек"
        WHERE "Логин_Пользователя" = arg_login_buyer) < стоимость_вещи
    THEN
        RAISE EXCEPTION 'У пользователя % не хватает денег для покупки вещи с данного лота',arg_login_buyer;

    ELSE


        INSERT INTO "Транзакции"("Логин_Пользователя", "Вид транзакции", "Сумма", "Дата транзакции")
        VALUES (arg_login_buyer, 'Покупка вещи', стоимость_вещи, current_timestamp);

        INSERT INTO "Транзакции"("Логин_Пользователя", "Вид транзакции", "Сумма", "Дата транзакции")
        VALUES (логин_продавца, 'Продажа вещи', стоимость_вещи, current_timestamp);

        INSERT INTO "Инвентарь"("Логин_Пользователя", "Внутриигровая_вещь ID")
        VALUES (arg_login_buyer, вещь_с_лота);

        DELETE
        FROM "Торговая площадка"
        WHERE "Торговая площадка".id = arg_torgovaya_ploshadka;


        UPDATE "Кошелек"
        SET "Баланс" = (SELECT "Баланс"
                        FROM "Кошелек"
                        WHERE "Логин_Пользователя" = arg_login_buyer) - стоимость_вещи
        WHERE "Логин_Пользователя" = arg_login_buyer;

        UPDATE "Кошелек"
        SET "Баланс"=(SELECT "Баланс"
                      FROM "Кошелек"
                      WHERE "Логин_Пользователя" = логин_продавца) + стоимость_вещи
        WHERE "Логин_Пользователя" = логин_продавца;
    END IF;
END;

$$ LANGUAGE plpgsql;



--процедура для отмены лота на торговой площадке
CREATE OR REPLACE PROCEDURE "УбратьЛотСТорговойПлощадки"(arg_login varchar(10), arg_torgovaya_ploshadka integer)
AS
$$
DECLARE
    вещь_с_лота integer;
BEGIN

    IF (SELECT "Логин"
        FROM "Пользователь"
        WHERE "Пользователь"."Логин" = arg_login) IS NULL
    THEN
        RAISE EXCEPTION 'Пользователь % не существует', arg_login;
    END IF;

    IF (SELECT id
        FROM "Торговая площадка"
        WHERE "Торговая площадка".id = arg_torgovaya_ploshadka) IS NULL
    THEN
        RAISE EXCEPTION 'На Торговой Площадке отсутствует заданный лот!';
    END IF;

    вещь_с_лота = (SELECT "Внутриигровая_вещь ID"
                   FROM "Торговая площадка"
                   WHERE "Торговая площадка".id = arg_torgovaya_ploshadka);


    IF (SELECT "Логин_Пользователя"
        FROM "Торговая площадка"
        WHERE "Торговая площадка".id = arg_torgovaya_ploshadka) = arg_login
    THEN
        INSERT INTO "Инвентарь"("Логин_Пользователя", "Внутриигровая_вещь ID")
        VALUES (arg_login, вещь_с_лота);

        DELETE
        FROM "Торговая площадка"
        WHERE "Торговая площадка".id = arg_torgovaya_ploshadka;

    ELSE
        RAISE EXCEPTION 'Вы не можете отменить заданный лот, т.к не являетесь его владельцем!';
    end if;

END;

$$ LANGUAGE plpgsql;









