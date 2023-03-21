package com.example.weblabb4.controller;

import com.example.weblabb4.config.jwt.JwtFilter;
import com.example.weblabb4.config.jwt.JwtProvider;
import com.example.weblabb4.entity.PointEntity;
import com.example.weblabb4.requests.PointRequest;
import com.example.weblabb4.service.PointService;
import com.example.weblabb4.util.PointCreator;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureException;
import io.jsonwebtoken.UnsupportedJwtException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(path = "/points")
@CrossOrigin(origins = "*")
public class PointController {

    @Autowired
    private PointService pointService;

    @Autowired
    private JwtProvider jwtProvider;

    @Autowired
    private JwtFilter jwtFilter;

    @Autowired
    private PointCreator pointCreator;

    @PostMapping("/add")
    public ResponseEntity<String> addPoint(@Valid @RequestBody PointRequest pointRequest, BindingResult bindingResult, HttpServletRequest httpServletRequest) {

        if (bindingResult.hasErrors()) {
            List<FieldError> errors = bindingResult.getFieldErrors();
            String errorMessage = "";
            for (FieldError error : errors) {
                errorMessage = errorMessage + error.getDefaultMessage() + '\n';
            }
            return ResponseEntity.badRequest().body(errorMessage);
        }
        try {
            Long executeTimeStart = System.nanoTime();
            PointEntity pointEntity = pointCreator.createPoint(pointRequest, executeTimeStart);
            String username = jwtProvider.getUsernameFromToken(jwtFilter.getTokenFromRequest(httpServletRequest));
            pointEntity.setUsername(username);
            pointService.savePoint(pointEntity);
        } catch (NumberFormatException e) {
            return ResponseEntity.badRequest().body("Coordinates must be numbers!");
        } catch (ExpiredJwtException expiredJwtException) {
            return ResponseEntity.badRequest().body("Expired JWT token.");
        } catch (UnsupportedJwtException unsupportedJwtException) {
            return ResponseEntity.badRequest().body("Unsupported JWT token.");
        } catch (MalformedJwtException malformedJwtException) {
            return ResponseEntity.badRequest().body("Invalid JWT token.");
        } catch (SignatureException signatureException) {
            return ResponseEntity.badRequest().body("Invalid JWT signature.");
        } catch (IllegalArgumentException illegalArgumentException) {
            return ResponseEntity.badRequest().body("JWT token compact of handler are invalid.");
        }
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping("/get")
    private ResponseEntity<Object> getPoints(HttpServletRequest httpServletRequest) {
        try {
            String username = jwtProvider.getUsernameFromToken(jwtFilter.getTokenFromRequest(httpServletRequest));
            return new ResponseEntity<>(pointService.getAllPointsByUsername(username), HttpStatus.OK);
        } catch (ExpiredJwtException expiredJwtException) {
            return ResponseEntity.badRequest().body("Expired JWT token.");
        } catch (UnsupportedJwtException unsupportedJwtException) {
            return ResponseEntity.badRequest().body("Unsupported JWT token.");
        } catch (MalformedJwtException malformedJwtException) {
            return ResponseEntity.badRequest().body("Invalid JWT token.");
        } catch (SignatureException signatureException) {
            return ResponseEntity.badRequest().body("Invalid JWT signature.");
        } catch (IllegalArgumentException illegalArgumentException) {
            return ResponseEntity.badRequest().body("JWT token compact of handler are invalid.");
        }
    }

    @DeleteMapping("/delete")
    private ResponseEntity<Object> deletePoints(HttpServletRequest httpServletRequest) {
        try {
            String username = jwtProvider.getUsernameFromToken(jwtFilter.getTokenFromRequest(httpServletRequest));
            pointService.deleteAllPointsByUsername(username);
            return new ResponseEntity<>("All points successfully deleted", HttpStatus.OK);
        } catch (ExpiredJwtException expiredJwtException) {
            return ResponseEntity.badRequest().body("Expired JWT token.");
        } catch (UnsupportedJwtException unsupportedJwtException) {
            return ResponseEntity.badRequest().body("Unsupported JWT token.");
        } catch (MalformedJwtException malformedJwtException) {
            return ResponseEntity.badRequest().body("Invalid JWT token.");
        } catch (SignatureException signatureException) {
            return ResponseEntity.badRequest().body("Invalid JWT signature.");
        } catch (IllegalArgumentException illegalArgumentException) {
            return ResponseEntity.badRequest().body("JWT token compact of handler are invalid.");
        }
    }

}
