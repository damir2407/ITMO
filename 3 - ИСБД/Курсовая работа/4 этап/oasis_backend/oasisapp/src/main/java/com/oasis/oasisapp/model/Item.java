package com.oasis.oasisapp.model;


import javax.persistence.*;

@Entity
@Table(name = "items")
public class Item {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "game_id")
    private Game game;

    private String name;

    private String rarity;

    private String itemUrl;

    public Item(Game game, String name, String rarity, String itemUrl) {
        this.game = game;
        this.name = name;
        this.rarity = rarity;
        this.itemUrl = itemUrl;
    }

    public Item() {
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRarity() {
        return rarity;
    }

    public void setRarity(String rarity) {
        this.rarity = rarity;
    }

    public String getItemUrl() {
        return itemUrl;
    }

    public void setItemUrl(String item_url) {
        this.itemUrl = item_url;
    }
}
