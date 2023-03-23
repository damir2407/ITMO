package com.oasis.oasisapp.repository;

import com.oasis.oasisapp.model.Game;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface GameRepository extends JpaRepository<Game, Long> {
    @Query(value = "SELECT * FROM games WHERE upper(games.name)=upper(:game_name)", nativeQuery = true)
    Optional<Game> getByNameInAnyRegister(@Param("game_name") String name);

    @Query(value = "SELECT * FROM games WHERE upper(games.game_url)=upper(:game_url)", nativeQuery = true)
    Optional<Game> getByGameUrlInAnyRegister(@Param("game_url") String game_url);


    boolean existsByName(String name);

    Optional<Game> getGameByName(String name);


}
