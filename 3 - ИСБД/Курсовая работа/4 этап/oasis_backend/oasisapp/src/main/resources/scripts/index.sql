CREATE INDEX games_index ON games USING hash (name);

CREATE INDEX shop_index ON shop USING hash(game_id);

CREATE INDEX library_index ON library USING btree(user_login, game_id);

CREATE INDEX inventory_index ON inventory USING btree(user_login, item_id);

CREATE INDEX wallets_id ON users USING hash (wallet_id);





