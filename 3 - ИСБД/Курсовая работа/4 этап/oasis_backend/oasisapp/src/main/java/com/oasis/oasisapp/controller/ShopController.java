package com.oasis.oasisapp.controller;

import com.oasis.oasisapp.service.ShopService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/shop")
@CrossOrigin(origins = "*", maxAge = 3600)
public class ShopController {
    private final ShopService shopService;

    public ShopController(ShopService shopService) {
        this.shopService = shopService;
    }

    @GetMapping()
    public ResponseEntity<?> getGamesByNameAndGenres(@RequestParam String gameName,
                                                     @RequestParam List<String> genres)  {
        return ResponseEntity.ok(shopService.getEntriesByGameNameAndGenres(gameName,
                genres));

    }


}
