package com.example.blps_lab1.controller;

import com.example.blps_lab1.dto.request.SignInRequest;
import com.example.blps_lab1.dto.request.SignUpRequest;
import com.example.blps_lab1.dto.response.SuccessResponse;
import com.example.blps_lab1.model.Jwt;
import com.example.blps_lab1.service.UserService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Validated
@RestController
@RequestMapping("/auth")
@CrossOrigin(origins = "*", maxAge = 3600)
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("login")
    public ResponseEntity<?> authUser(@Valid @RequestBody SignInRequest signInRequest) {
        Jwt jwt = userService.authUser(signInRequest);
        return new ResponseEntity<>(new SuccessResponse(jwt.getToken()), HttpStatus.OK);
    }

    @PostMapping()
    public ResponseEntity<?> registerUser(@Valid @RequestBody SignUpRequest signUpRequest) {
        userService.saveUser(signUpRequest);
        return new ResponseEntity<>(new SuccessResponse("Пользователь успешно зарегистрирован!"), HttpStatus.CREATED);
    }


}
