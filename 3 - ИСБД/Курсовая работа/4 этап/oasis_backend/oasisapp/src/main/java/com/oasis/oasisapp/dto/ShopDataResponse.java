package com.oasis.oasisapp.dto;

import java.util.List;

public class ShopDataResponse {
    private String gameName;

    private Double price;

    private String pictureShop;

    private List<String> genres;

    public ShopDataResponse(String gameName, Double price, String pictureShop, List<String> genres) {
        this.gameName = gameName;
        this.price = price;
        this.pictureShop = pictureShop;
        this.genres=genres;
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

    public String getPictureShop() {
        return pictureShop;
    }

    public void setPictureShop(String pictureShop) {
        this.pictureShop = pictureShop;
    }

    public List<String> getGenres() {
        return genres;
    }

    public void setGenres(List<String> genres) {
        this.genres = genres;
    }
}
