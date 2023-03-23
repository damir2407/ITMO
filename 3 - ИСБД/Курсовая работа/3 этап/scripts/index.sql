CREATE INDEX Инвентарь_index ON "Инвентарь" USING btree("Логин_Пользователя", "Внутриигровая_вещь ID");

CREATE UNIQUE INDEX Библиотека_index ON "Библиотека" USING btree("Логин_Пользователя", "Игра_ID");

CREATE INDEX Кошелек_index ON "Кошелек" USING hash("Логин_Пользователя");

CREATE INDEX Игра_index ON "Игра" USING hash ("Название");

CREATE INDEX Магазин_index ON "Магазин" USING hash("Игра_ID");