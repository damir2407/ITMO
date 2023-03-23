package com.oasis.oasisapp.controller;


import com.oasis.oasisapp.dto.MessageResponse;
import com.oasis.oasisapp.dto.UploadGameRequest;
import com.oasis.oasisapp.service.DevService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Validated
@RestController
@RequestMapping("/dev")
@CrossOrigin(origins = "*", maxAge = 3600)
public class DevController {
    private final DevService devService;

    public DevController(DevService devService) {
        this.devService = devService;
    }


    @PreAuthorize("hasRole('DEV')")
    @PostMapping()
    public ResponseEntity<?> uploadGame(@Valid @RequestBody UploadGameRequest uploadGameRequest) {

        devService.uploadGame(uploadGameRequest);

        return new ResponseEntity<>(new MessageResponse("Игра успешно загружена!"), HttpStatus.CREATED);

    }
}
