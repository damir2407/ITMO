package com.oasis.oasisapp.repository;

import com.oasis.oasisapp.model.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ItemRepository extends JpaRepository<Item, Long> {

    @Query(value = "SELECT * FROM items WHERE items.game_id=:game_id AND items.name=:item_name AND items.rarity=:item_rarity", nativeQuery = true)
    Optional<Item> existsItemByGameAndNameAndRarity(@Param("game_id") Long game_id, @Param("item_name") String item_name, @Param("item_rarity") String item_rarity);


    @Query(value = "SELECT * FROM items WHERE items.game_id=:game_id", nativeQuery = true)
    Optional<List<Item>> getAllItemsByGameId(@Param("game_id") Long game_id);



}
