--В таблице игра должна быть уникальная запись с игрой
CREATE OR REPLACE TRIGGER checkGameOnUnique
    BEFORE INSERT
    ON games
    FOR EACH ROW
EXECUTE FUNCTION checkGameOnUniqueFunction();

--В таблице магазин должна быть уникальная запись с игрой
CREATE OR REPLACE TRIGGER checkShopOnUnique
    BEFORE INSERT
    ON shop
    FOR EACH ROW
EXECUTE FUNCTION checkShopOnUniqueFunction();

--В таблице библиотека должна быть уникальная запись с игрой у пользователя
CREATE OR REPLACE TRIGGER checkLibraryOnUnique
    BEFORE INSERT
    ON library
    FOR EACH ROW
EXECUTE FUNCTION checkLibraryOnUniqueFunction();

--В инвентаре должна быть уникальная запись с вещью, но с разным количеством
CREATE OR REPLACE TRIGGER checkInventoryOnUnique
    AFTER INSERT
    ON inventory
    FOR EACH ROW
EXECUTE FUNCTION checkInventoryOnUniqueFunction();

--Пользователь может продавать только те вещи, которые есть у него в инвентаре
CREATE OR REPLACE TRIGGER checkMarketOnSell
    AFTER INSERT
    ON market
    FOR EACH ROW
EXECUTE FUNCTION sellItemOnMarket();