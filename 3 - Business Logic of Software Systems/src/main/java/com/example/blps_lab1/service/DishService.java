package com.example.blps_lab1.service;

import com.example.blps_lab1.dto.request.AddDishRequest;
import com.example.blps_lab1.dto.request.UpdateDishRequest;
import com.example.blps_lab1.exception.*;
import com.example.blps_lab1.model.Dish;
import com.example.blps_lab1.repository.DishRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class DishService {

    private final DishRepository dishRepository;

    public DishService(DishRepository dishRepository) {
        this.dishRepository = dishRepository;
    }


    public Dish findDishByName(String name) {
        return dishRepository.findDishByName(name).orElseThrow(() -> new ResourceNotFoundException("Блюдо " + name + " не найдено"));
    }

    public void saveDish(AddDishRequest addDishRequest) {
        Dish dish = new Dish(addDishRequest.getDishName(), addDishRequest.getDescription());
        if (dishRepository.existsDishByName(addDishRequest.getDishName()))
            throw new ResourceAlreadyExistException("Блюдо " + addDishRequest.getDishName() + " уже есть в базе данных!");
        dishRepository.save(dish);
    }

    public void deleteDish(Long dishId) {
        if (!dishRepository.existsById(dishId))
            throw new ResourceNotFoundException("Блюдо с id=" + dishId + " не найдено!");
        dishRepository.deleteById(dishId);
    }

    public void updateDish(Long dishId, UpdateDishRequest updateDishRequest) {
        Dish dish = dishRepository.findDishById(dishId).orElseThrow(() -> new ResourceNotFoundException("Блюдо с id=" + dishId + " не существует!"));
        if (updateDishRequest.getName() != null) {
            dish.setName(updateDishRequest.getName());
        }
        if (updateDishRequest.getDescription() != null) {
            dish.setDescription(updateDishRequest.getDescription());
        }
        dishRepository.save(dish);
    }

    public Dish getDish(Long dishId) {
        Dish dish = dishRepository.findDishById(dishId).orElseThrow(() -> new ResourceNotFoundException("Блюдо с id=" + dishId + " не существует!"));
        return dish;
    }

    public Page<Dish> getAllDish(int pageNum, int pageSize) {
        if (pageNum < 1 || pageSize < 1)
            throw new IllegalPageParametersException("Номер страницы и размер страницы должны быть больше 1!");
        Pageable pageRequest = createPageRequest(pageNum - 1, pageSize);
        Page<Dish> dishes = dishRepository.findAll(pageRequest);
        if (dishes.getTotalPages() < pageNum)
            throw new ResourceNotFoundException("На указанной странице не найдено записей!");
        return dishes;
    }

    private Pageable createPageRequest(int pageNum, int pageSize) {
        return PageRequest.of(pageNum, pageSize);
    }
}
