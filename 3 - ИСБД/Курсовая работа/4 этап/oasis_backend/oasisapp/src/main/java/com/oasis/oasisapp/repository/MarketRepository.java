package com.oasis.oasisapp.repository;

import com.oasis.oasisapp.model.Market;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
public interface MarketRepository extends JpaRepository<Market, Long> {

    @Query(value = "SELECT * FROM market", nativeQuery = true)
    Optional<List<Market>> getAllSlots();

    @Query(value = "SELECT * FROM market WHERE market.id=:id", nativeQuery = true)
    Optional<Market> getMarketById(@Param("id") Long id);


    @Query(value = "SELECT * FROM market WHERE market.item_id IN (SELECT items.id FROM items WHERE upper(items.name) LIKE concat('%',upper(:item_name),'%'))", nativeQuery = true)
    Optional<List<Market>> getAllFromMarketByItemNameFilter(@Param("item_name") String item_name);


    @Transactional
    void deleteMarketById(Long id);
}
