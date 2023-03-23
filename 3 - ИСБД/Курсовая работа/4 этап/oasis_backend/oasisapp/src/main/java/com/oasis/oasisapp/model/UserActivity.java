package com.oasis.oasisapp.model;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "user_activity")
public class UserActivity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_login")
    private User user;

    private String activityText;

    private Timestamp sendDate;

    public UserActivity(User user, String activityText, Timestamp sendDate) {
        this.user = user;
        this.activityText = activityText;
        this.sendDate = sendDate;
    }

    public UserActivity() {
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

    public String getActivityText() {
        return activityText;
    }

    public void setActivityText(String activity_text) {
        this.activityText = activity_text;
    }

    public Timestamp getSendDate() {
        return sendDate;
    }

    public void setSendDate(Timestamp send_date) {
        this.sendDate = send_date;
    }
}
