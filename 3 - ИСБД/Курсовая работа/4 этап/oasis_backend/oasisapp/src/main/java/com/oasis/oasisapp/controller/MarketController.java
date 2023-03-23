package com.oasis.oasisapp.controller;


import com.oasis.oasisapp.dto.ItemBuyRequest;
import com.oasis.oasisapp.dto.ItemSellRequest;
import com.oasis.oasisapp.model.Game;
import com.oasis.oasisapp.model.Item;
import com.oasis.oasisapp.model.Market;
import com.oasis.oasisapp.model.User;
import com.oasis.oasisapp.service.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@Validated
@RestController
@RequestMapping("/market")
@CrossOrigin(origins = "*", maxAge = 3600)
public class MarketController {

    private final UserService userService;
    private final MarketService marketService;

    private final ItemService itemService;

    private final GameService gameService;

    private final InventoryService inventoryService;

    private final AuthService authService;

    public MarketController(UserService userService, MarketService marketService, ItemService itemService,
                            GameService gameService, InventoryService inventoryService, AuthService authService) {
        this.userService = userService;
        this.marketService = marketService;
        this.itemService = itemService;
        this.gameService = gameService;
        this.inventoryService = inventoryService;
        this.authService = authService;
    }

    @PreAuthorize("hasRole('USER')")
    @PostMapping("/sell")
    public ResponseEntity<?> sellItem(@Valid @RequestBody ItemSellRequest itemSellRequest,
                                      HttpServletRequest httpServletRequest) {
        String login = authService.getLoginFromToken(httpServletRequest);
        User user = userService.getUserByLogin(login);
        Game game = gameService.getGameByName(itemSellRequest.getGameName());
        Item item = itemService.getItemByGameNameItemNameAndRarity(game, itemSellRequest.getItemName()
                , itemSellRequest.getRarity());
        marketService.save(user, item, itemSellRequest.getPrice());
        return new ResponseEntity<>(HttpStatus.CREATED);
    }


    @GetMapping()
    public ResponseEntity<?> getAllSlots(@RequestParam String gameName,
                                         @RequestParam String itemName) {
        return ResponseEntity.ok(marketService.getEntriesByGameNameAndItemName(itemName
                , gameName));

    }


    @PreAuthorize("hasRole('USER')")

    @PostMapping("/buy")
    public ResponseEntity<?> buyItem(@RequestBody ItemBuyRequest itemBuyRequest, HttpServletRequest httpServletRequest)  {
        String login = authService.getLoginFromToken(httpServletRequest);
        User user = userService.getUserByLogin(login);
        Market market = marketService.getMarketById(itemBuyRequest.getMarketId());

        userService.checkBalanceToBuyGame(user, market.getPrice());

        Game game = gameService.getGameByName(itemBuyRequest.getGameName());
        Item item = itemService.getItemByGameNameItemNameAndRarity(game,
                itemBuyRequest.getItemName(), itemBuyRequest.getRarity());
        inventoryService.saveItemToCustomer(user, item);

        userService.chargeBalanceCustomer(login, market.getPrice());
        userService.replenishBalanceSeller(market.getUser().getLogin(), market.getPrice());

        marketService.deleteSlot(market.getId());

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);

    }

}
