--Пользователь может продавать только те вещи, которые есть у него в инвентаре
CREATE OR REPLACE TRIGGER "ПроверкаВещиПередПродажей"
    AFTER INSERT
    ON "Торговая площадка"
    FOR EACH ROW
EXECUTE FUNCTION "ПродажаНаТорговойПлощадке"();

--Пользователь может содержать игры в библиотеке, которые есть в магазине и не может содержать повторные записи
CREATE OR REPLACE TRIGGER "ПроверкаИгрВБиблиотеке"
    AFTER INSERT
    ON "Библиотека"
    FOR EACH ROW
EXECUTE FUNCTION "ПроверитьНаНаличиеВМагазине"();


--Пользователь может оставлять обзор лишь на игры, которые имеет в библиотеке
CREATE OR REPLACE TRIGGER "ПроверкаВРуководстве"
    AFTER INSERT
    ON "Руководство"
    FOR EACH ROW
EXECUTE FUNCTION "ПроверитьРуководствоВБиблиотеке"();


--Пользователь может опубликовывать моды лишь на игры, которые имеет в библиотеке
CREATE OR REPLACE TRIGGER "ПроверкаВМастерской"
    AFTER INSERT
    ON "Мастерская"
    FOR EACH ROW
EXECUTE FUNCTION "ПроверитьМастерскуюВБиблиотеке"();


--У созданного пользователя должен быть кошелек с 0 балансом
CREATE OR REPLACE TRIGGER "СозданиеКошелька"
    AFTER INSERT
    ON users
    FOR EACH ROW
EXECUTE FUNCTION "СоздатьПустойКошелек"();


--Если создается группа, то должно увеличиться количество участников и записаться значение в учатники группы
CREATE OR REPLACE TRIGGER "ПроверкаГруппы"
    AFTER INSERT
    ON "Группа"
    FOR EACH ROW
EXECUTE FUNCTION "ПроверитьГруппу"();


--Пользователь может писать сообщения только в те группы, в которых состоит
CREATE OR REPLACE TRIGGER "ПроверкаСообщений"
    AFTER INSERT
    ON "Список сообщений"
    FOR EACH ROW
EXECUTE FUNCTION "ПроверитьСообщенияНаГруппу"(); 


--В инвентаре должна быть уникальная запись с вещью, но с разным количеством
CREATE OR REPLACE TRIGGER "ПроверкаНаУникИнвентарь"
    AFTER INSERT
    ON "Инвентарь"
    FOR EACH ROW
EXECUTE FUNCTION "ПроверитьИнвентарь"();



--В таблице игра должна быть уникальная запись с игрой
CREATE OR REPLACE TRIGGER "ПроверкаНаУникИгру"
    AFTER INSERT
    ON "Игра"
    FOR EACH ROW
EXECUTE FUNCTION "ПроверитьНаУникальностьИгру"();



--В таблице магазин должна быть уникальная запись с игрой
CREATE OR REPLACE TRIGGER "ПроверкаНаУникМагазин"
    AFTER INSERT
    ON "Магазин"
    FOR EACH ROW
EXECUTE FUNCTION "ПроверитьНаУникальностьМагазин"();


--В таблице кошелек должна быть уникальная запись с пользователем
CREATE OR REPLACE TRIGGER "ПроверкаНаУникКошелек"
    AFTER INSERT
    ON "Кошелек"
    FOR EACH ROW
EXECUTE FUNCTION "ПроверитьНаУникальностьКошелек"();

