package com.oasis.oasisapp.repository;

import com.oasis.oasisapp.model.Guide;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface GuideRepository extends JpaRepository<Guide, Long> {


    @Query(value = "SELECT * FROM guides WHERE guides.game_id IN (SELECT games.id FROM games WHERE games.name=:gameName)", nativeQuery = true)
    Optional<Page<Guide>> findByGameName(@Param("gameName") String gameName, Pageable pageable);


}
