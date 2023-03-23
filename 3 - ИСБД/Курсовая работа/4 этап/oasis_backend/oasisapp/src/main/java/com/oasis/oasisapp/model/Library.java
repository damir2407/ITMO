package com.oasis.oasisapp.model;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "library")
public class Library {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_login")
    private User user;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "game_id")
    private Game game;

    private Timestamp lastRunDate;

    public Library(User user, Game game) {
        this.user = user;
        this.game = game;
    }

    public Library() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Game getGame() {
        return game;
    }

    public void setGame(Game game) {
        this.game = game;
    }

    public Timestamp getLastRunDate() {
        return lastRunDate;
    }

    public void setLastRunDate(Timestamp last_run_date) {
        this.lastRunDate = last_run_date;
    }
}
