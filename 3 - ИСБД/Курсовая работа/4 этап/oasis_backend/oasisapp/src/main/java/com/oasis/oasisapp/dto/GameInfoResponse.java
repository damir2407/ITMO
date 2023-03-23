package com.oasis.oasisapp.dto;

import java.util.List;

public class GameInfoResponse {
    private String gameName;
    private Double price;
    private List<String> genres;
    private String developmentDate;
    private String devLogin;
    private String gameDescription;
    private String pictureCover;
    private String pictureGameplay1;
    private String pictureGameplay2;
    private String pictureGameplay3;


    public GameInfoResponse(String gameName, Double price, List<String> genres,
                            String developmentDate, String devLogin, String gameDescription,
                            String pictureCover, String pictureGameplay1, String pictureGameplay2,
                            String pictureGameplay3) {
        this.gameName = gameName;
        this.price = price;
        this.genres = genres;
        this.developmentDate = developmentDate;
        this.devLogin = devLogin;
        this.gameDescription = gameDescription;
        this.pictureCover = pictureCover;
        this.pictureGameplay1 = pictureGameplay1;
        this.pictureGameplay2 = pictureGameplay2;
        this.pictureGameplay3 = pictureGameplay3;
    }

    public String getGameName() {
        return gameName;
    }

    public void setGameName(String gameName) {
        this.gameName = gameName;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public List<String> getGenres() {
        return genres;
    }

    public void setGenres(List<String> genres) {
        this.genres = genres;
    }

    public String getDevelopmentDate() {
        return developmentDate;
    }

    public void setDevelopmentDate(String developmentDate) {
        this.developmentDate = developmentDate;
    }

    public String getDevLogin() {
        return devLogin;
    }

    public void setDevLogin(String devLogin) {
        this.devLogin = devLogin;
    }

    public String getGameDescription() {
        return gameDescription;
    }

    public void setGameDescription(String gameDescription) {
        this.gameDescription = gameDescription;
    }

    public String getPictureCover() {
        return pictureCover;
    }

    public void setPictureCover(String pictureCover) {
        this.pictureCover = pictureCover;
    }

    public String getPictureGameplay1() {
        return pictureGameplay1;
    }

    public void setPictureGameplay1(String pictureGameplay1) {
        this.pictureGameplay1 = pictureGameplay1;
    }

    public String getPictureGameplay2() {
        return pictureGameplay2;
    }

    public void setPictureGameplay2(String pictureGameplay2) {
        this.pictureGameplay2 = pictureGameplay2;
    }

    public String getPictureGameplay3() {
        return pictureGameplay3;
    }

    public void setPictureGameplay3(String pictureGameplay3) {
        this.pictureGameplay3 = pictureGameplay3;
    }
}
