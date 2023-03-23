package com.oasis.oasisapp.repository;

import com.oasis.oasisapp.model.Game;
import com.oasis.oasisapp.model.Shop;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ShopRepository extends JpaRepository<Shop, Long> {
    Boolean existsByGame(Game game);

    @Query(value = "SELECT * FROM shop", nativeQuery = true)
    Optional<List<Shop>> getAllFromShop();


    @Query(value = "SELECT * FROM  shop WHERE shop.game_id IN (SELECT games.id FROM games WHERE upper(games.name) LIKE concat('%',upper(:game_name),'%'))", nativeQuery = true)
    Optional<List<Shop>> getAllFromShopByGameNameFilter(@Param("game_name") String game_name);

    @Query(value = "SELECT * FROM shop where shop.game_id IN (SELECT games.id FROM games WHERE games.name=:game_name)", nativeQuery = true)
    Optional<Shop> getShopByGameName(@Param("game_name") String game_name);

    @Query(value = "SELECT shop.picture_shop FROM shop where shop.game_id IN (SELECT games.id FROM games WHERE games.name=:game_name)", nativeQuery = true)
    String getGameShopPictureByGameName(@Param("game_name") String game_name);

}
