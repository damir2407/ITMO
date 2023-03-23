package com.oasis.oasisapp.dto;


public class LibraryDataResponse {
    private String gameName;


    private String pictureShop;

    private String gameUrl;

    private String lastRunDate;

    public LibraryDataResponse(String gameName, String pictureShop, String lastRunDate, String gameUrl) {
        this.gameName = gameName;
        this.pictureShop = pictureShop;
        this.lastRunDate = lastRunDate;
        this.gameUrl = gameUrl;
    }

    public String getLastRunDate() {
        return lastRunDate;
    }

    public void setLastRunDate(String lastRunDate) {
        this.lastRunDate = lastRunDate;
    }

    public String getGameName() {
        return gameName;
    }

    public void setGameName(String gameName) {
        this.gameName = gameName;
    }

    public String getPictureShop() {
        return pictureShop;
    }

    public void setPictureShop(String pictureShop) {
        this.pictureShop = pictureShop;
    }

    public String getGameUrl() {
        return gameUrl;
    }

    public void setGameUrl(String gameUrl) {
        this.gameUrl = gameUrl;
    }
}
