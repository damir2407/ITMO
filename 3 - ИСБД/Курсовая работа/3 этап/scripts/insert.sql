INSERT INTO "Пользователь"("Логин", "Пароль", "Статус", "Дата последнего входа","Почтовый адрес")
VALUES ('damir2407', 'aaaaaaa272', 'В сети', now(),'dasdad@gmail.com'),
       ('susaasus1', 'asjdasjdajhd2',  'Не в сети', '2002-12-02','sadasdasda@gmail.com'),
       ('eldar11', 'dsadsan2m22', 'В сети', now(),'asdsadasd@gmail.com'),
       ('access','asd23jhajshd','Не в сети','2013-05-09','zxcza@gmail.com'),
       ('krutoi12','sadajklsl','В сети', now(),'q2211212@gmail.com'),
       ('fish229','sadnesshapiness','Не в сети','2022-10-13','saasdsadsa@gmail.com'),
       ('houses','passwddd','Не в сети', '2022-10-12','2131xa@gmail.com'),
       ('dass','ssmnmsms','В сети','2021-11-12','asd21s@gmail.com');

INSERT INTO "Роль"("Название роли")
VALUES ('ROLE_USER'),
       ('ROLE_DEV');


INSERT INTO "Разработчик"("Логин", "Пароль")
VALUES ('rob_david', 'asdsadkd2'),
       ('mike_tyson', 'znxmzcnz'),
       ('smith12', 'qwertdka'),
       ('opo21', 'azertya'),
       ('zxccursed', 'asdkdlkld2'),
       ('sdmmmd', '2kkskskw', 'В сети');


INSERT INTO "Игра"("Название", "Жанр", "Дата разработки")
VALUES ('Dota2', 'Стратегия', '2011-02-03'),
       ('Diablo1', 'MMO-RPG', '2008-12-11'),
       ('Counter Strike 1.6', 'Шутер', '2000-06-12'),
       ('Fall guys', 'Песочница', '2020-12-12'),
       ('Divinity', 'RPG', '2016-01-01'),
       ('Genshin Impact', 'RPG', '2020-11-10'),
       ('Grand Turismo 1', 'Гонки', '2006-10-01');
       


INSERT INTO "Группа"("Название","Логин_Создателя")
VALUES ('Heroes And Magic', 'damir2407'),
       ('TBGITW', 'susaasus1'),
       ('2ofamericazmostwanted', 'krutoi12'),
       ('doggers', 'houses');

INSERT INTO "Транзакции"("Логин_Пользователя", "Вид транзакции", "Сумма", "Дата транзакции")
VALUES ('damir2407', 'Покупка игры', 600, '2022-10-09'),
       ('susaasus1', 'Продажа вещи на торговой площадке', 250, '2022-06-11'),
       ('eldar11', 'Покупка вещи на тороговой площадке', 250, '2022-06-11'),
       ('damir2407', 'Покупка вещи на торговой площадке',2312, '2022-09-10'),
       ('krutoi12', 'Продажа вещи на торговой площадке',2312, '2022-09-10'),
       ('krutoi12', 'Покупка игры', 1250, '2022-09-11');


INSERT INTO "Список сообщений"("Группа_ID", "Логин_Пользователя", "Содержимое сообщения", "Дата добавления")
VALUES (1, 'damir2407', 'Всем привет!!!', now()),
       (2, 'susaasus1', 'Ребят, кто пойдет в ММ?', '2022-10-13'),
       (3, 'krutoi12', 'Пойду погуляю с собакой', '2022-10-10'),
       (4, 'houses', 'Кто напы пойдет?', '2022-10-13');

INSERT INTO "Создатели игры"("Логин_Разработчика", "Игра_ID")
VALUES ('rob_david', 1),
       ('mike_tyson', 1),
       ('zxccursed', 2),
       ('mike_tyson', 2),
       ('sdmmmd', 3),
       ('sdmmmd', 4),
       ('sdmmmd', 5),
       ('zxccursed', 3),
       ('zxccursed', 5),
       ('opo21', 6),
       ('opo21', 5),
       ('opo21', 7),
       ('sdmmmd', 7);


INSERT INTO "Магазин"("Игра_ID", "Стоимость", "Рейтинг", "Описание игры")
VALUES (1, 600, 4.5,
        'компьютерная многопользовательская командная игра жанра стратегия в реальном времени с элементами компьютерной ролевой игры '),
       (2, 215.29, 4,
        'События игры разворачиваются во вселенной, разделенной на мир смертных людей (Санктуарий), райский мир и горящий ад'),
       (3, 0, 4.9, 'самая популярная и стабильная версия соревновательного командного шутера с видом от первого лица.'),
       (4, 1250, 3.2, 'игра в жанрах королевской битвы, платформера и аркадной игры'),
       (5, 550, 2, 'пошаговая партийная компьютерная ролевая игра'),
       (6, 420, 2.3, 'это красочная анимешная Action RPG с открытым миром от китайской компании HoYoverse'),
       (7, 300, 5, 'гонки от первого лица с невероятной физикой и графикой');


INSERT INTO "Внутриигровая вещь"("Игра_ID", "Название", "Редкость")
VALUES (1, 'Helmet of the death', 'Обычная'),
       (1, 'Crimson God', 'Легендарная'),
       (2, 'Flag of glory', 'Обычная'),
       (2, 'White desk', 'Редкая'),
       (4, 'Rainbow top skin', 'Обычная'),
       (4, 'Red bottom skin', 'Обычная'),
       (5, 'Stick of true', 'Мистическая'),
       (5, 'Red wine', 'Легендарная'),
       (6, 'Forgotten dragon', 'Легендарная'),
       (7, 'Metallic turbo wheels', 'Мистическая');


INSERT INTO "Инвентарь"("Логин_Пользователя", "Внутриигровая_вещь ID")
VALUES ('damir2407', 2),
       ('damir2407', 3),
       ('damir2407', 4),
       ('damir2407', 4),
       ('krutoi12', 2),
       ('susaasus1', 9),
       ('houses', 4),
       ('eldar11', 5),
       ('susaasus1', 5),
       ('susaasus1',3),
       ('susaasus1',3),
       ('eldar11',1);



INSERT INTO "Торговая площадка"("Логин_Пользователя", "Внутриигровая_вещь ID", "Стоимость")
VALUES ('susaasus1', 9, 250),
       ('krutoi12', 2, 2312),
       ('damir2407', 2, 123.11),
       ('damir2407', 3, 2312),
       ('houses', 4, 3332),
       ('eldar11', 5, 211),
       ('susaasus1', 5, 314);



CALL "ПополнитьБаланс"('damir2407',10000);
CALL "ПополнитьБаланс"('krutoi12',10000);
CALL "ПополнитьБаланс"('susaasus1',10000);
CALL "ПополнитьБаланс"('eldar11',10000);
CALL "ПополнитьБаланс"('houses',10000);

INSERT INTO "Библиотека"("Логин_Пользователя", "Игра_ID", "Дата последнего запуска")
VALUES ('damir2407', 1, '2022-10-01'),
       ('damir2407', 2, '2022-10-11'),
       ('krutoi12', 1, '2022-09-10'),
       ('susaasus1', 6, '2020-07-10'),
       ('susaasus1', 4, '2021-09-12'),
       ('eldar11', 4, '2021-05-23'),
       ('houses', 2, '2021-10-14'),
       ('houses', 1, '2020-12-07'),
       ('houses',7,'2020-12-07');


INSERT INTO "Руководство"("Логин_Пользователя", "Игра_ID", "Содержимое", "Дата добавления")
VALUES ('damir2407', 1, 'Отличное время проводжение с баночкой холодного пива', '2022-09-12'),
       ('damir2407', 2, 'Игра супер!', '2022-10-17'),
       ('eldar11', 4, '“Играя за кота, Вы можете пить из лужи, драть ковёр, прыгать на стол, ронять всё что можете сдвинуть, кататься в ведре и помогать роботам в их стремлении к свету.”', '2022-12-29'),
       ('houses', 7, 'Игра 10/10, рекомендую всем!', '2022-08-19');


INSERT INTO "Мастерская"("Логин_Пользователя", "Игра_ID", "Название мода", "Описание", "Дата добавления")
VALUES ('damir2407', 1, 'Industrial craft',
        'Модификация, добавляющая промышленное производство: металлургию, электротехнику, ядерную энергетику, агропромышленность, пивоварение.',
        '2022-09-12'),
       ('damir2407', 2, 'Divine RPG',
        'Массово расширяет игру, добавляя в мир большое разнообразие мобов, измерений, инструментов, оружия, брони, блоков, предметов и многое другое.',
        '2022-10-17'),
       ('susaasus1', 6, 'Blood Magic',
        'Модификация, с магической тематикой. Она добавляет новый источник энергии: кровь.', '2021-03-12');


