package com.example.blps_lab1.controller;

import com.example.blps_lab1.dto.request.AddDishRequest;
import com.example.blps_lab1.dto.request.UpdateDishRequest;
import com.example.blps_lab1.dto.response.DishResponse;
import com.example.blps_lab1.dto.response.SuccessResponse;
import com.example.blps_lab1.model.Dish;
import com.example.blps_lab1.service.DishService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Validated
@RestController
@RequestMapping("/dish")
@CrossOrigin(origins = "*", maxAge = 3600)
public class DishController {
    private final DishService dishService;

    public DishController(DishService dishService) {
        this.dishService = dishService;
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping()
    public ResponseEntity<?> addDish(@Valid @RequestBody AddDishRequest addDishRequest) {
        dishService.saveDish(addDishRequest);
        return new ResponseEntity<>(new SuccessResponse("Блюдо " + addDishRequest.getDishName() + " успешно добавлено в базу!"), HttpStatus.CREATED);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping()
    public ResponseEntity<?> deleteDish(@RequestParam("dishId") Long dishId) {
        dishService.deleteDish(dishId);
        return new ResponseEntity<>(new SuccessResponse("Блюдо с id=" + dishId + " успешно удалено!"), HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping()
    public ResponseEntity<?> updateDish(@RequestParam("dishId") Long dishId,
                                        @Valid @RequestBody UpdateDishRequest updateDishRequest) {
        dishService.updateDish(dishId, updateDishRequest);
        return new ResponseEntity<>(new SuccessResponse("Блюдо с id=" + dishId + " успешно обнавлено!"), HttpStatus.OK);
    }

    @PreAuthorize("hasAnyRole('ADMIN','USER')")
    @GetMapping()
    public ResponseEntity<?> getAllDishes(@RequestParam(value = "page", defaultValue = "1") int page,
                                          @RequestParam(value = "size", defaultValue = "10") int size) {
        return new ResponseEntity<>(dishService.getAllDish(page, size).getContent(), HttpStatus.OK);
    }

    @PreAuthorize("hasAnyRole('ADMIN','USER')")
    @GetMapping("{dishId}")
    public ResponseEntity<?> getDish(@PathVariable Long dishId) {
        Dish dish = dishService.getDish(dishId);
        return new ResponseEntity<>(new DishResponse(dish.getId(), dish.getName(), dish.getDescription()), HttpStatus.OK);
    }
}
