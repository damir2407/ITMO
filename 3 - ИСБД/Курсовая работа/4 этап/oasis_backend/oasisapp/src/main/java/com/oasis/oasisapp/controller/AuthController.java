package com.oasis.oasisapp.controller;

import com.oasis.oasisapp.dto.SignInRequest;
import com.oasis.oasisapp.dto.MessageResponse;
import com.oasis.oasisapp.dto.SignUpRequest;
import com.oasis.oasisapp.dto.UserDataResponse;
import com.oasis.oasisapp.service.AuthService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;


@Validated
@RestController
@RequestMapping("/auth")
@CrossOrigin(origins = "*", maxAge = 3600)
public class AuthController {


    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }



    @PostMapping("login")
    public ResponseEntity<?> authUser(@Valid @RequestBody SignInRequest signInRequest) {
        UserDataResponse userDataResponse = authService.loginAsUser(signInRequest.getLogin(), signInRequest.getPassword());
        return ResponseEntity.ok(userDataResponse);


    }

    @PostMapping()
    public ResponseEntity<?> registerUser(@Valid @RequestBody SignUpRequest signUpRequest) {

        authService.saveUser(signUpRequest.getLogin(), signUpRequest.getPassword(),
                signUpRequest.getEmail());
        return new ResponseEntity<>(new MessageResponse("Пользователь успешно зарегистрирован!"), HttpStatus.CREATED);

    }


}
