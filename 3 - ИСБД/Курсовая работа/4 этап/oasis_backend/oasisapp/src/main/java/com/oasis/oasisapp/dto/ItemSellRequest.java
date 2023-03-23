package com.oasis.oasisapp.dto;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

public class ItemSellRequest {
    private String gameName;
    private String itemName;
    private String rarity;


    @NotNull(message = "Укажите цену!")
    @Min(value = 1, message = "Цена должна быть от 1$ до 500$")
    @Max(value = 500, message = "Цена должна быть от 1$ до 500$")
    private Double price;


    public String getGameName() {
        return gameName;
    }

    public void setGameName(String gameName) {
        this.gameName = gameName;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
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
}
