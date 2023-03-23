package com.oasis.oasisapp.repository;

import com.oasis.oasisapp.model.Inventory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface InventoryRepository extends JpaRepository<Inventory, Long> {

    @Query(value = "SELECT * FROM inventory WHERE inventory.user_login=:userLogin", nativeQuery = true)
    Optional<Page<Inventory>>findAllByUser(@Param("userLogin") String userLogin, Pageable pageable);
}
