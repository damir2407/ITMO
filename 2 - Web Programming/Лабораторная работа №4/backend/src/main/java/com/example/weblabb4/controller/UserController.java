package com.example.weblabb4.controller;

import com.example.weblabb4.config.jwt.JwtProvider;
import com.example.weblabb4.entity.UserEntity;
import com.example.weblabb4.exception.UserAlreadyExistException;
import com.example.weblabb4.exception.UserNotFoundException;
import com.example.weblabb4.requests.AuthRequest;
import com.example.weblabb4.requests.RegistrationRequest;
import com.example.weblabb4.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@CrossOrigin(origins = "*")
@RequestMapping(path = "/user")
@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private JwtProvider jwtProvider;

    @PostMapping("/register")
    public ResponseEntity<String> registerUser(@Valid @RequestBody RegistrationRequest registrationRequest, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            List<FieldError> errors = bindingResult.getFieldErrors();
            String errorMessage = "";
            for (FieldError error : errors) {
                errorMessage = errorMessage + error.getDefaultMessage() + '\n';
            }
            return ResponseEntity.badRequest().body(errorMessage);
        }


        UserEntity user = new UserEntity();
        user.setPassword(registrationRequest.getPassword());
        user.setUsername(registrationRequest.getUsername());
        try {
            userService.saveUser(user);
        } catch (UserAlreadyExistException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
        return ResponseEntity.ok("User registered successfully");
    }


    @PostMapping("/auth")
    public ResponseEntity<String> auth(@Valid @RequestBody AuthRequest authRequest, BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            List<FieldError> errors = bindingResult.getFieldErrors();
            String errorMessage = "";
            for (FieldError error : errors) {
                errorMessage = errorMessage + error.getDefaultMessage() + '\n';
            }
            return ResponseEntity.badRequest().body(errorMessage);
        }

        try {
            UserEntity userEntity = userService.findByUsernameAndPassword(authRequest.getUsername(), authRequest.getPassword());
            String token = jwtProvider.generateToken(userEntity.getUsername());
            return ResponseEntity.ok(token);
        } catch (UserNotFoundException exception) {
            return ResponseEntity.badRequest().body(exception.getMessage());
        }

    }
}
