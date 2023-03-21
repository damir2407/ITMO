package com.example.weblabb4.service;

import com.example.weblabb4.entity.PointEntity;
import com.example.weblabb4.repository.PointRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

@Service
public class PointService {

    @Autowired
    private PointRepo pointRepo;


    public PointEntity savePoint(PointEntity pointEntity) {
        pointRepo.save(pointEntity);
        return pointEntity;
    }

    public List<PointEntity> getAllPointsByUsername(String username) {
        List<PointEntity> returnedList = pointRepo.getAllByUsername(username);
        returnedList.sort(Comparator.comparing(PointEntity::getCurrentTime));
        Collections.reverse(returnedList);
        return returnedList;
    }

    @Transactional
    public void deleteAllPointsByUsername(String username) {
        pointRepo.deleteAllByUsername(username);
    }

}
