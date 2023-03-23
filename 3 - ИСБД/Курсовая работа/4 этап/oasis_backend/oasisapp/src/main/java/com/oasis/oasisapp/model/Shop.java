package com.oasis.oasisapp.model;

import javax.persistence.*;

@Entity
@Table(name = "shop")
public class Shop {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    private Game game;

    private Double price;

    private String description;

    private String pictureCover;

    private String picture_shop;

    private String pictureGamePlay1;

    private String pictureGamePlay2;

    private String pictureGamePlay3;

    public Shop(Game game, Double price, String description, String pictureCover, String picture_shop,
                String pictureGamePlay1, String pictureGamePlay2, String pictureGamePlay3) {
        this.game = game;
        this.price = price;
        this.description = description;
        this.pictureCover = pictureCover;
        this.picture_shop = picture_shop;
        this.pictureGamePlay1 = pictureGamePlay1;
        this.pictureGamePlay2 = pictureGamePlay2;
        this.pictureGamePlay3 = pictureGamePlay3;
    }

    public Shop() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Game getGame() {
        return game;
    }

    public void setGame(Game game) {
        this.game = game;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPictureCover() {
        return pictureCover;
    }

    public void setPictureCover(String picture_cover) {
        this.pictureCover = picture_cover;
    }

    public String getPicture_shop() {
        return picture_shop;
    }

    public void setPicture_shop(String picture_shop) {
        this.picture_shop = picture_shop;
    }

    public String getPictureGamePlay1() {
        return pictureGamePlay1;
    }

    public void setPictureGamePlay1(String picture_gameplay_1) {
        this.pictureGamePlay1 = picture_gameplay_1;
    }

    public String getPictureGamePlay2() {
        return pictureGamePlay2;
    }

    public void setPictureGamePlay2(String picture_gameplay_2) {
        this.pictureGamePlay2 = picture_gameplay_2;
    }

    public String getPictureGamePlay3() {
        return pictureGamePlay3;
    }

    public void setPictureGamePlay3(String picture_gameplay_3) {
        this.pictureGamePlay3 = picture_gameplay_3;
    }
}
