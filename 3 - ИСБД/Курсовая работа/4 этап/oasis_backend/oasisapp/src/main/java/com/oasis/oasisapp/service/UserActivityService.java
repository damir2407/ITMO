package com.oasis.oasisapp.service;

import com.oasis.oasisapp.dto.UserActivityResponse;
import com.oasis.oasisapp.exception.ResourceNotFoundException;
import com.oasis.oasisapp.model.User;
import com.oasis.oasisapp.model.UserActivity;
import com.oasis.oasisapp.repository.UserActivityRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

@Service
public class UserActivityService {
    private final UserActivityRepository userActivityRepository;

    public UserActivityService(UserActivityRepository userActivityRepository) {
        this.userActivityRepository = userActivityRepository;
    }

    public void saveUserActivity(User user, String text) {
        userActivityRepository.save(new UserActivity(user, text, new Timestamp(System.currentTimeMillis())));
    }

    public List<UserActivityResponse> getAllActivities(String login, int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<UserActivity> data = userActivityRepository.getAllByUserLogin(login, pageable).orElseThrow(ResourceNotFoundException::new);
        List<UserActivity> userActivityList = data.getContent();

        List<UserActivityResponse> userActivityResponseList = new ArrayList<>();
        userActivityList.forEach(userActivity -> {
            userActivityResponseList.add(new UserActivityResponse(userActivity.getSendDate().toString().substring(0,
                    userActivity.getSendDate().toString().indexOf('.')),
                    userActivity.getActivityText()));
        });

        userActivityResponseList.sort(Comparator.comparing(UserActivityResponse::getSendDate));
        Collections.reverse(userActivityResponseList);


        return userActivityResponseList;
    }
}
