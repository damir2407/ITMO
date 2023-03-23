package com.oasis.oasisapp.dto;

public class LastGamesResponse {
    private String shopPicture;
    private String gameName;
    private String lastEnterDate;

    public LastGamesResponse(String shopPicture, String gameName, String lastEnterDate) {
        this.shopPicture = shopPicture;
        this.gameName = gameName;
        this.lastEnterDate = lastEnterDate;
    }

    public String getShopPicture() {
        return shopPicture;
    }

    public void setShopPicture(String shopPicture) {
        this.shopPicture = shopPicture;
    }

    public String getGameName() {
        return gameName;
    }

    public void setGameName(String gameName) {
        this.gameName = gameName;
    }

    public String getLastEnterDate() {
        return lastEnterDate;
    }

    public void setLastEnterDate(String lastEnterDate) {
        this.lastEnterDate = lastEnterDate;
    }
}
