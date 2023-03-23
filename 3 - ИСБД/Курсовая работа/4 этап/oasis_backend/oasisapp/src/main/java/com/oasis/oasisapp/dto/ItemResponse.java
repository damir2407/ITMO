package com.oasis.oasisapp.dto;


public class ItemResponse {
    private String itemPicture;
    private String itemName;
    private String gameName;
    private String rarity;
    private Integer amount;

    private String gamePicture;

    public ItemResponse(String itemPicture, String itemName, String gameName, String rarity, Integer amount, String gamePicture) {
        this.itemPicture = itemPicture;
        this.itemName = itemName;
        this.gameName = gameName;
        this.rarity = rarity;
        this.amount = amount;
        this.gamePicture = gamePicture;
    }

    public String getItemPicture() {
        return itemPicture;
    }

    public void setItemPicture(String itemPicture) {
        this.itemPicture = itemPicture;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
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

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public String getGamePicture() {
        return gamePicture;
    }

    public void setGamePicture(String gamePicture) {
        this.gamePicture = gamePicture;
    }
}
