package com.example.blps_lab1.service;


import com.example.blps_lab1.dto.request.AddIngredientRequest;
import com.example.blps_lab1.dto.request.UpdateIngredientRequest;
import com.example.blps_lab1.exception.*;
import com.example.blps_lab1.model.Ingredients;
import com.example.blps_lab1.repository.IngredientsRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class IngredientsService {

    private final IngredientsRepository ingredientsRepository;

    public IngredientsService(IngredientsRepository ingredientsRepository) {
        this.ingredientsRepository = ingredientsRepository;
    }

    public Ingredients findIngredientByName(String name) {
        return ingredientsRepository.findIngredientsByName(name).orElseThrow(() -> new ResourceNotFoundException("Ингредиент " + name + " не найден"));
    }


    public List<Ingredients> findAllIngredientsByNames(List<String> names) {
        List<Ingredients> ingredientsList = new ArrayList<>();
        for (String ingredient_name : names) {
            Ingredients ingredient = findIngredientByName(ingredient_name);
            ingredientsList.add(ingredient);
        }
        return ingredientsList;
    }

    public void saveIngredient(AddIngredientRequest addIngredientRequest) {
        Ingredients ingredient = new Ingredients(addIngredientRequest.getIngredientName(), addIngredientRequest.getDescription());
        if (ingredientsRepository.existsIngredientsByName(addIngredientRequest.getIngredientName()))
            throw new ResourceAlreadyExistException("Ингредиент " + addIngredientRequest.getIngredientName() + " уже есть в базе данных!");
        ingredientsRepository.save(ingredient);
    }

    public void deleteIngredient(Long ingredientId) {
        if (!ingredientsRepository.existsById(ingredientId))
            throw new ResourceNotFoundException("Ингредиент с id=" + ingredientId + " не существует!");
        ingredientsRepository.deleteById(ingredientId);
    }

    public void updateIngredient(Long ingredientId, UpdateIngredientRequest updateIngredientRequest) {
        Ingredients ingredient = ingredientsRepository.findIngredientsById(ingredientId).orElseThrow(() -> new ResourceNotFoundException("Ингредиент с id=" + ingredientId + " не существует!"));
        if (updateIngredientRequest.getName() != null) {
            ingredient.setName(updateIngredientRequest.getName());
        }
        if (updateIngredientRequest.getDescription() != null) {
            ingredient.setDescription(updateIngredientRequest.getDescription());
        }
        ingredientsRepository.save(ingredient);
    }

    public Ingredients getIngredient(Long ingredientId) {
        Ingredients ingredient = ingredientsRepository.findIngredientsById(ingredientId).orElseThrow(() -> new ResourceNotFoundException("Ингредиент с id=" + ingredientId + " не существует!"));
        return ingredient;
    }

    public Page<Ingredients> getIngredientsPage(int pageNum, int pageSize) {
        if (pageNum < 1 || pageSize < 1)
            throw new IllegalPageParametersException("Номер страницы и размер страницы должны быть больше 1!");
        Pageable pageRequest = createPageRequest(pageNum - 1, pageSize);
        Page<Ingredients> ingredients = ingredientsRepository.findAll(pageRequest);
        if (ingredients.getTotalPages() < pageNum)
            throw new ResourceNotFoundException("На указанной странице не найдено записей!");
        return ingredients;
    }

    private Pageable createPageRequest(int pageNum, int pageSize) {
        return PageRequest.of(pageNum, pageSize);
    }
}
