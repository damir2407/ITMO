package com.oasis.oasisapp.controller;

import com.oasis.oasisapp.service.AuthService;
import com.oasis.oasisapp.service.LibraryService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/library")
@CrossOrigin(origins = "*", maxAge = 3600)
public class LibraryController {

    private final AuthService authService;
    private final LibraryService libraryService;

    public LibraryController(AuthService authService, LibraryService libraryService) {
        this.authService = authService;
        this.libraryService = libraryService;
    }

    @PreAuthorize("hasRole('USER')")
    @GetMapping("{gameName}")
    public ResponseEntity<?> getGamesByName(@PathVariable String gameName,
                                            HttpServletRequest httpServletRequest) {
        String login = authService.getLoginFromToken(httpServletRequest);

        return ResponseEntity.ok(libraryService.getEntriesByGameName(gameName, login));
    }

    @PreAuthorize("hasRole('USER')")

    @PatchMapping("{gameName}")
    public ResponseEntity<?> enterGame(@PathVariable String gameName,
                                       HttpServletRequest httpServletRequest) {
        String login = authService.getLoginFromToken(httpServletRequest);
        libraryService.enterTheGame(login, gameName);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }


    @GetMapping("count/{login}")
    public ResponseEntity<?> getGamesCount(@PathVariable String login) {
        return ResponseEntity.ok(libraryService.getCountOfUserGames(login));
    }

    @GetMapping("last-games/{login}")
    public ResponseEntity<?> getLastGames(@PathVariable String login) {
        return ResponseEntity.ok(libraryService.getUserLastGames(login));
    }


}
