package com.oasis.oasisapp.model;


import javax.persistence.*;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "games")
public class Game {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private LocalDate developmentDate;

    public Game(String name, LocalDate developmentDate) {
        this.name = name;
        this.developmentDate = developmentDate;
    }


    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "game_genres",
            joinColumns = @JoinColumn(name = "game_id"),
            inverseJoinColumns = @JoinColumn(name = "genre_id"))
    private Set<Genre> genres = new HashSet<>();

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "dev_login")
    private User user;


    private String gameUrl;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Game() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getDevelopmentDate() {
        return developmentDate;
    }

    public void setDevelopmentDate(LocalDate development_date) {
        this.developmentDate = development_date;
    }

    public Set<Genre> getGenres() {
        return genres;
    }

    public void setGenres(Set<Genre> genres) {
        this.genres = genres;
    }

    public String getGameUrl() {
        return gameUrl;
    }

    public void setGameUrl(String game_url) {
        this.gameUrl = game_url;
    }
}
