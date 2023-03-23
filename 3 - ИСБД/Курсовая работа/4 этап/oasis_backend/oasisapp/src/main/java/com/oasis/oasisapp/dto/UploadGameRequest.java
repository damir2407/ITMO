package com.oasis.oasisapp.dto;

import org.hibernate.validator.constraints.URL;

import javax.validation.constraints.*;
import java.util.Set;

public class UploadGameRequest {

    @NotBlank(message = "Укажите название игры!")
    @Size(min = 1, max = 64, message = "Укажите название игры! От 1 до 64 символов")
    private String name;

    @NotBlank(message = "Укажите описание игры!")
    private String description;

    @NotNull(message = "Укажите жанр(ы)!")
    private Set<String> genres;

    @NotBlank(message = "Укажите ссылку на игру")
    @Size(max = 256, message = "Укажите ссылку на игру! До 256 символов")
    @URL(message = "Неверный формат ссылки!")
    private String gameUrl;


    @NotNull(message = "Укажите цену! Укажите цену равную 0$, если хотите выпустить бесплатную игру")
    @Min(value = 0, message = "Цена должна быть от 0$ до 500$")
    @Max(value = 500, message = "Цена должна быть от 0$ до 500$")
    private Double price;

    @NotBlank(message = "Укажите ссылку на картинку-обложку")
    @Size(max = 256, message = "Укажите ссылку на картинку-обложку! До 256 символов")
    @URL(message = "Неверный формат ссылки!")
    private String pictureCover;

    @NotBlank(message = "Укажите ссылку на картинку в магазине")
    @Size(max = 256, message = "Укажите ссылку на картинку в магазине! До 256 символов")
    @URL(message = "Неверный формат ссылки!")
    private String pictureShop;

    @NotBlank(message = "Укажите ссылку на картинку-геймплей 1")
    @Size(max = 256, message = "Укажите ссылку на картинку-геймплей 1! До 256 символов")
    @URL(message = "Неверный формат ссылки!")
    private String pictureGameplay1;

    @NotBlank(message = "Укажите ссылку на картинку-геймплей 2")
    @Size(max = 256, message = "Укажите ссылку на картинку-геймплей 2! До 256 символов")
    @URL(message = "Неверный формат ссылки!")
    private String pictureGameplay2;

    @NotBlank(message = "Укажите ссылку на картинку-геймплей 3")
    @Size(max = 256, message = "Укажите ссылку на картинку-геймплей 3! До 256 символов")
    @URL(message = "Неверный формат ссылки!")
    private String pictureGameplay3;

    private String devLogin;


    @Size(max = 20, message = "Укажите название обычной вещи! До 20 символов")
    private String commonItemName;

    @Size(max = 256, message = "Укажите ссылку на картинку обычной вещи! До 256 символов")
    @URL(message = "Неверный формат ссылки!")
    private String commonItemUrl;


    @Size(max = 20, message = "Укажите название редкой вещи! До 20 символов")
    private String rareItemName;

    @Size(max = 256, message = "Укажите ссылку на картинку редкой вещи! До 256 символов")
    @URL(message = "Неверный формат ссылки!")
    private String rareItemUrl;

    @Size(max = 20, message = "Укажите название легендарной вещи! До 20 символов")
    private String legendaryItemName;

    @Size(max = 256, message = "Укажите ссылку на картинку легендарной вещи! До 256 символов")
    @URL(message = "Неверный формат ссылки!")
    private String legendaryItemUrl;

    public String getDevLogin() {
        return devLogin;
    }

    public void setDevLogin(String devLogin) {
        this.devLogin = devLogin;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Set<String> getGenres() {
        return genres;
    }

    public void setGenres(Set<String> genres) {
        this.genres = genres;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getPictureCover() {
        return pictureCover;
    }

    public void setPictureCover(String pictureCover) {
        this.pictureCover = pictureCover;
    }

    public String getPictureShop() {
        return pictureShop;
    }

    public void setPictureShop(String pictureShop) {
        this.pictureShop = pictureShop;
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


    public String getGameUrl() {
        return gameUrl;
    }

    public void setGameUrl(String gameUrl) {
        this.gameUrl = gameUrl;
    }

    public String getCommonItemName() {
        return commonItemName;
    }

    public void setCommonItemName(String commonItemName) {
        this.commonItemName = commonItemName;
    }

    public String getCommonItemUrl() {
        return commonItemUrl;
    }

    public void setCommonItemUrl(String commonItemUrl) {
        this.commonItemUrl = commonItemUrl;
    }

    public String getRareItemName() {
        return rareItemName;
    }

    public void setRareItemName(String rareItemName) {
        this.rareItemName = rareItemName;
    }

    public String getRareItemUrl() {
        return rareItemUrl;
    }

    public void setRareItemUrl(String rareItemUrl) {
        this.rareItemUrl = rareItemUrl;
    }

    public String getLegendaryItemName() {
        return legendaryItemName;
    }

    public void setLegendaryItemName(String legendaryItemName) {
        this.legendaryItemName = legendaryItemName;
    }

    public String getLegendaryItemUrl() {
        return legendaryItemUrl;
    }

    public void setLegendaryItemUrl(String legendaryItemUrl) {
        this.legendaryItemUrl = legendaryItemUrl;
    }


}
