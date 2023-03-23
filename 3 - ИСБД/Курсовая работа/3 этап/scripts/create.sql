CREATE TABLE users
(
    login             varchar(10) PRIMARY KEY,

    password          varchar(255) NOT NULL,

    status            varchar(32) DEFAULT 'Не в сети',
    last_login_date   timestamp,
    email             varchar(64) NOT NULL,
    registration_date date        NOT NULL
);


CREATE TABLE "Роль"
(
    id              serial primary key,
    "Название роли" varchar(20) NOT NULL
);



CREATE TABLE "Разработчик"
(
    "Логин"  varchar(10) PRIMARY KEY,

    "Пароль" varchar(32) NOT NULL
        CONSTRAINT password_check
            CHECK (length("Пароль") > 5)
);



CREATE TABLE "Игра"
(
    ID                SERIAL PRIMARY KEY,
    "Название"        TEXT NOT NULL,
    "Жанр"            TEXT NOT NULL,
    "Дата разработки" DATE NOT NULL
);


CREATE TABLE "Группа"
(
    ID                      SERIAL PRIMARY KEY,
    "Название"              varchar(64) NOT NULL,
    "Количество участников" integer,
    "Логин_Создателя"       varchar(10) REFERENCES "Пользователь" ON DELETE CASCADE ON UPDATE CASCADE
);


CREATE TABLE "Кошелек"
(
    ID                   SERIAL PRIMARY KEY,
    "Логин_Пользователя" varchar(10) REFERENCES "Пользователь" ON DELETE CASCADE ON UPDATE CASCADE,

    "Баланс"             REAL DEFAULT 0
        CONSTRAINT check_balance
            CHECK ("Баланс" >= 0)

);


CREATE TABLE "Транзакции"
(
    ID                   SERIAL PRIMARY KEY,
    "Логин_Пользователя" varchar(10) REFERENCES "Пользователь" ON DELETE CASCADE ON UPDATE CASCADE,
    "Вид транзакции"     varchar(64) NOT NULL,
    "Сумма"              real        NOT NULL,
    "Дата транзакции"    timestamp   NOT NULL
);


CREATE TABLE "Участники Группы"
(
    "Группа_ID"          INTEGER REFERENCES "Группа" ON DELETE CASCADE ON UPDATE CASCADE,
    "Логин_Пользователя" varchar(10) REFERENCES "Пользователь" ON DELETE CASCADE ON UPDATE CASCADE,
    PRIMARY KEY ("Группа_ID", "Логин_Пользователя")
);


CREATE TABLE "Список сообщений"
(
    ID                     SERIAL PRIMARY KEY,
    "Группа_ID"            INTEGER REFERENCES "Группа" ON DELETE CASCADE ON UPDATE CASCADE,
    "Логин_Пользователя"   varchar(10) REFERENCES "Пользователь" ON DELETE CASCADE ON UPDATE CASCADE,
    "Содержимое сообщения" text      NOT NULL,
    "Дата добавления"      timestamp NOT NULL
);


CREATE TABLE "Создатели игры"
(
    "Логин_Разработчика" varchar(10) REFERENCES "Разработчик" ON DELETE CASCADE ON UPDATE CASCADE,
    "Игра_ID"            INTEGER REFERENCES "Игра" ON DELETE CASCADE ON UPDATE CASCADE,
    PRIMARY KEY ("Логин_Разработчика", "Игра_ID")
);


CREATE TABLE "Магазин"
(
    ID              serial primary key,
    "Игра_ID"       INTEGER REFERENCES "Игра" ON DELETE CASCADE ON UPDATE CASCADE,

    "Стоимость"     real DEFAULT (0)
        CONSTRAINT check_price
            CHECK ("Стоимость" >= 0),

    "Рейтинг"       real default (0)
        CONSTRAINT check_rating
            CHECK ( "Рейтинг" >= 0 AND "Рейтинг" <= 5),

    "Описание игры" text DEFAULT ('Описание отсутствует')

);


CREATE TABLE "Внутриигровая вещь"
(
    ID         serial primary key,
    "Игра_ID"  INTEGER REFERENCES "Игра" ON DELETE CASCADE ON UPDATE CASCADE,
    "Название" varchar(128) NOT NULL,
    "Редкость" varchar(64) DEFAULT ('Обычная')
);


CREATE TABLE "Торговая площадка"
(
    ID                      serial primary key,
    "Логин_Пользователя"    varchar(10) REFERENCES "Пользователь" ON DELETE CASCADE ON UPDATE CASCADE,
    "Внутриигровая_вещь ID" INTEGER REFERENCES "Внутриигровая вещь" ON DELETE CASCADE ON UPDATE CASCADE,

    "Стоимость"             real DEFAULT (0)
        CONSTRAINT check_price
            CHECK ("Стоимость" >= 0)
);


CREATE TABLE "Инвентарь"
(
    ID                      serial primary key,
    "Логин_Пользователя"    varchar(10) REFERENCES "Пользователь" ON DELETE CASCADE ON UPDATE CASCADE,
    "Внутриигровая_вещь ID" INTEGER REFERENCES "Внутриигровая вещь" ON DELETE CASCADE ON UPDATE CASCADE,
    "Количество"            INTEGER DEFAULT 0
);


CREATE TABLE "Библиотека"
(
    ID                        serial primary key,
    "Логин_Пользователя"      varchar(10) REFERENCES "Пользователь" ON DELETE CASCADE ON UPDATE CASCADE,
    "Игра_ID"                 INTEGER REFERENCES "Игра" ON DELETE CASCADE ON UPDATE CASCADE,
    "Дата последнего запуска" date
);


CREATE TABLE "Руководство"
(
    ID                   serial primary key,
    "Логин_Пользователя" varchar(10) REFERENCES "Пользователь" ON DELETE CASCADE ON UPDATE CASCADE,
    "Игра_ID"            INTEGER REFERENCES "Игра" ON DELETE CASCADE ON UPDATE CASCADE,
    "Содержимое"         text NOT NULL,
    "Дата добавления"    date NOT NULL
);


CREATE TABLE "Мастерская"
(
    ID                   serial primary key,
    "Логин_Пользователя" varchar(10) REFERENCES "Пользователь" ON DELETE CASCADE ON UPDATE CASCADE,
    "Игра_ID"            INTEGER REFERENCES "Игра" ON DELETE CASCADE ON UPDATE CASCADE,
    "Название мода"      varchar(64) NOT NULL,
    "Описание"           text DEFAULT ('Описание отсутствует'),
    "Дата добавления"    date        NOT NULL
);
