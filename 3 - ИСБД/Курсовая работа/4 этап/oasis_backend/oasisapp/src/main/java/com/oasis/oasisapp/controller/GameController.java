package com.oasis.oasisapp.controller;


import com.oasis.oasisapp.dto.AllGamesMarketResponse;
import com.oasis.oasisapp.dto.GameNameRequest;
import com.oasis.oasisapp.model.Game;
import com.oasis.oasisapp.model.User;
import com.oasis.oasisapp.service.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/game")
@CrossOrigin(origins = "*", maxAge = 3600)
public class GameController {

    private final GameService gameService;

    private final UserService userService;

    private final LibraryService libraryService;

    private final InventoryService inventoryService;

    private final ShopService shopService;

    private final AuthService authService;

    public GameController(GameService gameService,
                          UserService userService, LibraryService libraryService,
                          InventoryService inventoryService, ShopService shopService,
                          AuthService authService) {
        this.gameService = gameService;
        this.userService = userService;
        this.libraryService = libraryService;
        this.inventoryService = inventoryService;
        this.shopService = shopService;
        this.authService = authService;
    }

    @GetMapping("{gameName}")
    public ResponseEntity<?> getGameInfo(@PathVariable String gameName) {
        return ResponseEntity.ok(gameService.getGameInfo(gameName));
    }


    @GetMapping("check/{gameName}")
    public ResponseEntity<?> checkGameName(@PathVariable String gameName) {
        if (gameService.checkGameOnExist(gameName)) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }



    @PreAuthorize("hasRole('USER')")
    @PostMapping()
    public ResponseEntity<?> buyGame(@RequestBody GameNameRequest gameNameRequest, HttpServletRequest httpServletRequest) {
        String login = authService.getLoginFromToken(httpServletRequest);
        Game game = gameService.getGameByName(gameNameRequest.getGameName());
        User user = userService.getUserByLogin(login);
        libraryService.saveGameInUserLibrary(user, game);

        int status = inventoryService.saveInventory(user, game);
        return new ResponseEntity<>(status, HttpStatus.CREATED);
    }

    @GetMapping()
    public ResponseEntity<?> getAllGames(@RequestParam(value = "page", defaultValue = "0") int page,
                                         @RequestParam(value = "size", defaultValue = "10") int size) {
        return ResponseEntity.ok(gameService.getAllGames(page, size));
    }

    @PreAuthorize("hasAnyRole('USER','DEV')")
    @GetMapping()
    public ResponseEntity<?> getAllGamesByLogin(@RequestParam(value = "page", defaultValue = "0") int page,
                                                @RequestParam(value = "size", defaultValue = "10") int size,
                                                HttpServletRequest httpServletRequest) {
        String login = authService.getLoginFromToken(httpServletRequest);
        return ResponseEntity.ok(libraryService.getUserGames(login, page, size));
    }

    @GetMapping("market")
    public ResponseEntity<?> getAllGamesForMarket(@RequestParam(value = "page", defaultValue = "0") int page,
                                                  @RequestParam(value = "size", defaultValue = "10") int size) {
        List<Game> allGames = gameService.getAllGames(page, size);
        List<AllGamesMarketResponse> allGamesMarketResponses = new ArrayList<>();
        allGames.forEach(game -> {
            allGamesMarketResponses.add(new AllGamesMarketResponse(game.getName(),
                    shopService.getGamePicture(game.getName())));
        });
        return ResponseEntity.ok(allGamesMarketResponses);


    }


}
