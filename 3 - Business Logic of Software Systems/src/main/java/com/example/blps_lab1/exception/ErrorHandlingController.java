package com.example.blps_lab1.exception;

import com.example.blps_lab1.dto.response.ErrorResponse;
import jakarta.validation.ConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;


@ControllerAdvice
public class ErrorHandlingController {

    @ExceptionHandler({PermissionDeniedException.class})
    public ResponseEntity<ErrorResponse> handleForbiddenException(RuntimeException e) {
        ErrorResponse errorResponse = new ErrorResponse(e.getMessage());
        return new ResponseEntity<>(errorResponse, HttpStatus.FORBIDDEN);
    }

    @ExceptionHandler({ConstraintViolationException.class, MethodArgumentNotValidException.class,
            IllegalPageParametersException.class, BadCredentialsException.class, ResourceAlreadyExistException.class})
    public ResponseEntity<ErrorResponse> handleBadRequestException(RuntimeException e) {
        ErrorResponse errorResponse = new ErrorResponse(e.getMessage());
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler({UsernameNotFoundException.class, ResourceNotFoundException.class})
    public ResponseEntity<ErrorResponse> handleNotFoundException(RuntimeException e) {
        ErrorResponse errorResponse = new ErrorResponse(e.getMessage());
        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
    }

}
