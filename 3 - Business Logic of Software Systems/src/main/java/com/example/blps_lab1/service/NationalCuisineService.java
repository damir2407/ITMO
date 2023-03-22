package com.example.blps_lab1.service;

import com.example.blps_lab1.dto.request.AddCuisineRequest;
import com.example.blps_lab1.dto.request.UpdateCuisineRequest;
import com.example.blps_lab1.exception.*;
import com.example.blps_lab1.model.NationalCuisine;
import com.example.blps_lab1.repository.NationalCuisineRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class NationalCuisineService {
    private final NationalCuisineRepository nationalCuisineRepository;


    public NationalCuisineService(NationalCuisineRepository nationalCuisineRepository) {
        this.nationalCuisineRepository = nationalCuisineRepository;
    }

    public NationalCuisine findNationalCuisineByName(String cuisine) {
        return nationalCuisineRepository.findNationalCuisineByCuisine(cuisine).orElseThrow(() -> new ResourceNotFoundException("Нац. кухня " + cuisine + " не существует!"));
    }

    public void saveCuisine(AddCuisineRequest addCuisineRequest) {
        NationalCuisine cuisine = new NationalCuisine(addCuisineRequest.getCuisine());
        if (nationalCuisineRepository.existsNationalCuisineByCuisine(addCuisineRequest.getCuisine()))
            throw new ResourceAlreadyExistException("Национальная кухня " + addCuisineRequest.getCuisine() + " уже есть в базе данных!");
        nationalCuisineRepository.save(cuisine);
    }

    public void deleteCuisine(Long cuisineId) {
        if (!nationalCuisineRepository.existsById(cuisineId))
            throw new ResourceNotFoundException("Ингредиент с id=" + cuisineId + " не существует!");
        nationalCuisineRepository.deleteById(cuisineId);
    }

    public void updateCuisine(Long cuisineId, UpdateCuisineRequest updateCuisineRequest) {
        NationalCuisine nationalCuisine = nationalCuisineRepository.findNationalCuisineById(cuisineId).orElseThrow(() -> new ResourceNotFoundException("Кухни с id=" + cuisineId + " не существует!"));
        nationalCuisine.setCuisine(updateCuisineRequest.getCuisine());
        nationalCuisineRepository.save(nationalCuisine);
    }

    public NationalCuisine getCuisine(Long cuisineId) {
        NationalCuisine nationalCuisine = nationalCuisineRepository.findNationalCuisineById(cuisineId).orElseThrow(() -> new ResourceNotFoundException("Кухни с id=" + cuisineId + " не существует!"));
        return nationalCuisine;
    }

    public Page<NationalCuisine> getCuisinesPage(int pageNum, int pageSize) {
        if (pageNum < 1 || pageSize < 1)
            throw new IllegalPageParametersException("Номер страницы и размер страницы должны быть больше 1!");
        Pageable pageRequest = createPageRequest(pageNum - 1, pageSize);
        Page<NationalCuisine> cuisines = nationalCuisineRepository.findAll(pageRequest);
        if (cuisines.getTotalPages() < pageNum)
            throw new ResourceNotFoundException("На указанной странице не найдено записей!");
        return cuisines;
    }

    private Pageable createPageRequest(int pageNum, int pageSize) {
        return PageRequest.of(pageNum, pageSize);
    }
}
