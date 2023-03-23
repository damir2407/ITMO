package com.oasis.oasisapp.model;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "guides")
public class Guide {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_login")
    private User user;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "game_id")
    private Game game;

    private String guideText;

    private Timestamp sendDate;


    public Guide() {
    }

    public Guide(User user, Game game, String guideText, Timestamp sendDate) {
        this.user = user;
        this.game = game;
        this.guideText = guideText;
        this.sendDate = sendDate;
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

    public String getGuideText() {
        return guideText;
    }

    public void setGuideText(String guide_text) {
        this.guideText = guide_text;
    }

    public Timestamp getSendDate() {
        return sendDate;
    }

    public void setSendDate(Timestamp send_date) {
        this.sendDate = send_date;
    }
}
