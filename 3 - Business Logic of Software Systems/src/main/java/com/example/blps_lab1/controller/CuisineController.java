package com.example.blps_lab1.controller;

import com.example.blps_lab1.dto.request.AddCuisineRequest;
import com.example.blps_lab1.dto.request.UpdateCuisineRequest;
import com.example.blps_lab1.dto.response.CuisineResponse;
import com.example.blps_lab1.dto.response.SuccessResponse;
import com.example.blps_lab1.model.NationalCuisine;
import com.example.blps_lab1.service.NationalCuisineService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Validated
@RestController
@RequestMapping("/cuisine")
@CrossOrigin(origins = "*", maxAge = 3600)
public class CuisineController {
    private final NationalCuisineService cuisineService;

    public CuisineController(NationalCuisineService cuisineService) {
        this.cuisineService = cuisineService;
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping()
    public ResponseEntity<?> addCuisine(@Valid @RequestBody AddCuisineRequest addCuisineRequest) {
        cuisineService.saveCuisine(addCuisineRequest);

        return new ResponseEntity<>(new SuccessResponse("Кухня " + addCuisineRequest.getCuisine() + " успешно добавлена в базу!"), HttpStatus.CREATED);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping()
    public ResponseEntity<?> deleteCuisine(@RequestParam("cuisineId") Long cuisineId) {
        cuisineService.deleteCuisine(cuisineId);
        return new ResponseEntity<>(new SuccessResponse("Кухня с id=" + cuisineId + " успешно удален!"), HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping()
    public ResponseEntity<?> updateCuisine(@RequestParam("cuisineId") Long cuisineId,
                                           @Valid @RequestBody UpdateCuisineRequest updateCuisineRequest) {
        cuisineService.updateCuisine(cuisineId, updateCuisineRequest);
        return new ResponseEntity<>(new SuccessResponse("Кухня с id=" + cuisineId + " успешно обновлен!"), HttpStatus.OK);
    }

    @PreAuthorize("hasAnyRole('ADMIN','USER')")
    @GetMapping()
    public ResponseEntity<?> getAllCuisines(@RequestParam(value = "page", defaultValue = "1") int page,
                                            @RequestParam(value = "size", defaultValue = "10") int size) {
        return new ResponseEntity<>(cuisineService.getCuisinesPage(page, size).getContent(), HttpStatus.OK);
    }

    @PreAuthorize("hasAnyRole('ADMIN','USER')")
    @GetMapping("{cuisineId}")
    public ResponseEntity<?> getCuisine(@PathVariable Long cuisineId) {
        NationalCuisine nationalCuisine = cuisineService.getCuisine(cuisineId);
        return new ResponseEntity<>(new CuisineResponse(nationalCuisine.getId(), nationalCuisine.getCuisine()), HttpStatus.OK);
    }
}
