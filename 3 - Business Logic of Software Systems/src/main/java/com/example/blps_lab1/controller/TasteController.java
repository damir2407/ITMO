package com.example.blps_lab1.controller;

import com.example.blps_lab1.dto.request.AddTasteRequest;
import com.example.blps_lab1.dto.request.UpdateTasteRequest;
import com.example.blps_lab1.dto.response.SuccessResponse;
import com.example.blps_lab1.dto.response.TasteResponse;
import com.example.blps_lab1.model.Tastes;
import com.example.blps_lab1.service.TastesService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Validated
@RestController
@RequestMapping("/taste")
@CrossOrigin(origins = "*", maxAge = 3600)
public class TasteController {
    private final TastesService tastesService;

    public TasteController(TastesService tastesService) {
        this.tastesService = tastesService;
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping()
    public ResponseEntity<?> addTaste(@Valid @RequestBody AddTasteRequest addTasteRequest) {
        tastesService.saveTaste(addTasteRequest);
        return new ResponseEntity<>(new SuccessResponse("Вкус " + addTasteRequest.getTaste() + " успешно добавлен в базу!"), HttpStatus.CREATED);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping()
    public ResponseEntity<?> deleteTaste(@RequestParam("tasteId") Long tasteId) {
        tastesService.deleteTaste(tasteId);
        return new ResponseEntity<>(new SuccessResponse("Вкус с id=" + tasteId + " успешно удален!"), HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping()
    public ResponseEntity<?> updateTaste(@RequestParam("tasteId") Long tasteId,
                                         @Valid @RequestBody UpdateTasteRequest updateTasteRequest) {

        tastesService.updateTaste(tasteId, updateTasteRequest);
        return new ResponseEntity<>(new SuccessResponse("Вкус с id=" + tasteId + " успешно обновлен!"), HttpStatus.OK);
    }

    @PreAuthorize("hasAnyRole('ADMIN','USER')")
    @GetMapping()
    public ResponseEntity<?> getAllTastes(@RequestParam(value = "page", defaultValue = "1") int page,
                                          @RequestParam(value = "size", defaultValue = "10") int size) {
        return new ResponseEntity<>(tastesService.getAllTaste(page, size).getContent(), HttpStatus.OK);
    }

    @PreAuthorize("hasAnyRole('ADMIN','USER')")
    @GetMapping("{tasteId}")
    public ResponseEntity<?> getTaste(@PathVariable Long tasteId) {

        Tastes taste = tastesService.getTaste(tasteId);
        System.out.println(taste.getTaste());
        return new ResponseEntity<>(new TasteResponse(taste.getId(), taste.getTaste()), HttpStatus.OK);
    }
}
