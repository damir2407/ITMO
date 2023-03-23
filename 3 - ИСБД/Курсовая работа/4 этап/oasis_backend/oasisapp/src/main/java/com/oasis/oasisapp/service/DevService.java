package com.oasis.oasisapp.service;

import com.oasis.oasisapp.dto.UploadGameRequest;
import com.oasis.oasisapp.model.Game;
import org.springframework.stereotype.Service;

@Service
public class DevService {

    private final GameService gameService;

    private final ShopService shopService;

    private final ItemService itemService;

    public DevService(GameService gameService, ShopService shopService, ItemService itemService) {
        this.gameService = gameService;
        this.shopService = shopService;
        this.itemService = itemService;
    }


    public void uploadGame(UploadGameRequest uploadGameRequest) {
        Game game = gameService.saveGame(uploadGameRequest.getName(), uploadGameRequest.getGenres(), uploadGameRequest.getDevLogin(), uploadGameRequest.getGameUrl());
        shopService.saveShop(game, uploadGameRequest.getPrice(), uploadGameRequest.getDescription(),
                uploadGameRequest.getPictureCover(), uploadGameRequest.getPictureShop(), uploadGameRequest.getPictureGameplay1(),
                uploadGameRequest.getPictureGameplay2(), uploadGameRequest.getPictureGameplay3());

        if (!uploadGameRequest.getCommonItemName().isEmpty() && !uploadGameRequest.getCommonItemUrl().isEmpty())
            itemService.saveItem(game, uploadGameRequest.getCommonItemName(),
                    "Обычная", uploadGameRequest.getCommonItemUrl());

        if (!uploadGameRequest.getRareItemName().isEmpty() && !uploadGameRequest.getRareItemUrl().isEmpty())
            itemService.saveItem(game, uploadGameRequest.getRareItemName(),
                    "Редкая", uploadGameRequest.getRareItemUrl());

        if (!uploadGameRequest.getLegendaryItemName().isEmpty() && !uploadGameRequest.getLegendaryItemUrl().isEmpty())
            itemService.saveItem(game, uploadGameRequest.getLegendaryItemName(),
                    "Легендарная", uploadGameRequest.getLegendaryItemUrl());
    }
}
