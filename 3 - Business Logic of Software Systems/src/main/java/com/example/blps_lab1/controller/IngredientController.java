package com.example.blps_lab1.controller;

import com.example.blps_lab1.dto.request.AddIngredientRequest;
import com.example.blps_lab1.dto.request.UpdateIngredientRequest;
import com.example.blps_lab1.dto.response.IngredientResponse;
import com.example.blps_lab1.dto.response.SuccessResponse;
import com.example.blps_lab1.model.Ingredients;
import com.example.blps_lab1.service.IngredientsService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Validated
@RestController
@RequestMapping("/ingredient")
@CrossOrigin(origins = "*", maxAge = 3600)
public class IngredientController {
    private final IngredientsService ingredientsService;

    public IngredientController(IngredientsService ingredientsService) {
        this.ingredientsService = ingredientsService;
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping()
    public ResponseEntity<?> addIngredient(@Valid @RequestBody AddIngredientRequest addIngredientRequest) {
        ingredientsService.saveIngredient(addIngredientRequest);
        return new ResponseEntity<>(new SuccessResponse("Ингредиент " + addIngredientRequest.getIngredientName() + " успешно добавлено в базу!"), HttpStatus.CREATED);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping()
    public ResponseEntity<?> deleteIngredient(@RequestParam("ingredientId") Long ingredientId) {
        ingredientsService.deleteIngredient(ingredientId);
        return new ResponseEntity<>(new SuccessResponse("Ингредиент с id=" + ingredientId + " успешно удален!"), HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping()
    public ResponseEntity<?> updateIngredient(@RequestParam("ingredientId") Long ingredientId,
                                              @Valid @RequestBody UpdateIngredientRequest updateIngredientRequest) {
        ingredientsService.updateIngredient(ingredientId, updateIngredientRequest);
        return new ResponseEntity<>(new SuccessResponse("Ингредиент с id=" + ingredientId + " успешно обновлен!"), HttpStatus.OK);
    }

    @PreAuthorize("hasAnyRole('ADMIN','USER')")
    @GetMapping()
    public ResponseEntity<?> getAllIngredients(@RequestParam(value = "page", defaultValue = "1") int page,
                                               @RequestParam(value = "size", defaultValue = "10") int size) {
        return new ResponseEntity<>(ingredientsService.getIngredientsPage(page, size).getContent(), HttpStatus.OK);
    }

    @PreAuthorize("hasAnyRole('ADMIN','USER')")
    @GetMapping("{ingredientId}")
    public ResponseEntity<?> getIngredient(@PathVariable Long ingredientId) {
        Ingredients ingredient = ingredientsService.getIngredient(ingredientId);
        return new ResponseEntity<>(new IngredientResponse(ingredient.getId(), ingredient.getDescription(), ingredient.getName()), HttpStatus.OK);
    }

}

