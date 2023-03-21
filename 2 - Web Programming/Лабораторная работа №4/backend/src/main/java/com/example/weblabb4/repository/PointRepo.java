package com.example.weblabb4.repository;

import com.example.weblabb4.entity.PointEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PointRepo extends JpaRepository<PointEntity, Integer> {
    List<PointEntity> getAllByUsername(String username);
    void deleteAllByUsername(String username);


}
