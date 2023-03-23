package com.oasis.oasisapp.dto;

public class SlotsResponse {
    private String itemName;
    private String itemPicture;
    private String gameName;
    private String rarity;
    private Double price;

    private Long marketId;

    public SlotsResponse(String itemName, String itemPicture, String gameName, String rarity, Double price, Long marketId) {
        this.itemName = itemName;
        this.itemPicture = itemPicture;
        this.gameName = gameName;
        this.rarity = rarity;
        this.price = price;
        this.marketId = marketId;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getItemPicture() {
        return itemPicture;
    }

    public void setItemPicture(String itemPicture) {
        this.itemPicture = itemPicture;
    }

    public String getGameName() {
        return gameName;
    }

    public void setGameName(String gameName) {
        this.gameName = gameName;
    }

    public String getRarity() {
        return rarity;
    }

    public void setRarity(String rarity) {
        this.rarity = rarity;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Long getMarketId() {
        return marketId;
    }

    public void setMarketId(Long marketId) {
        this.marketId = marketId;
    }
}
