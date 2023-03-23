package com.oasis.oasisapp.dto;

public class AllGamesMarketResponse {
    private String gameName;
    private String gamePicture;


    public AllGamesMarketResponse(String gameName, String gamePicture) {
        this.gameName = gameName;
        this.gamePicture = gamePicture;
    }

    public String getGameName() {
        return gameName;
    }

    public void setGameName(String gameName) {
        this.gameName = gameName;
    }

    public String getGamePicture() {
        return gamePicture;
    }

    public void setGamePicture(String gamePicture) {
        this.gamePicture = gamePicture;
    }
}
