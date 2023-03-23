package com.oasis.oasisapp.repository;

import com.oasis.oasisapp.model.Library;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
public interface LibraryRepository extends JpaRepository<Library, Long> {

    @Query(value = "SELECT * FROM library WHERE library.user_login=:user_login", nativeQuery = true)
    Optional<List<Library>> getAllFromLibraryByUserLogin(@Param("user_login") String login);

    @Query(value = "SELECT * FROM library WHERE library.user_login=?1", nativeQuery = true)
    Optional<Page<Library>> findByUser(String user, Pageable pageable);

    @Query(value = "SELECT * FROM library WHERE library.game_id IN (SELECT games.id FROM games WHERE upper(games.name) LIKE concat('%',upper(:game_name),'%')) AND " +
            "library.user_login IN (SELECT users.login FROM users WHERE users.login=:user_login)", nativeQuery = true)
    Optional<List<Library>> getAllFromLibraryByGameNameFilter(@Param("game_name") String game_name, @Param("user_login") String login);


    @Query(value = "SELECT shop.picture_shop FROM shop WHERE shop.game_id IN (SELECT games.id FROM games WHERE games.name=:game_name)", nativeQuery = true)
    String getGameShopPictureByGameName(@Param("game_name") String game_name);


    @Transactional
    @Modifying
    @Query(value = "CALL enterthegame(:user_login,:game_name)", nativeQuery = true)
    void enterInGame(@Param("user_login") String user_login, @Param("game_name") String game_name);


    @Query(value = "SELECT COUNT(*) FROM library WHERE library.user_login=:user_login", nativeQuery = true)
    Integer getCountOfGames(@Param("user_login") String login);


}
