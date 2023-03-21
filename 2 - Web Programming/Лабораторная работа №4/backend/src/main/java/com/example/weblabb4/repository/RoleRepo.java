package com.example.weblabb4.repository;

import com.example.weblabb4.entity.RoleEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepo extends JpaRepository<RoleEntity, Long> {

    RoleEntity findByName(String name);

}
