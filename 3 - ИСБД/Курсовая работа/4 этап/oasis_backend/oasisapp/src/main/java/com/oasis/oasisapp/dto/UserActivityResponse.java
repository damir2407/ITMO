package com.oasis.oasisapp.dto;

public class UserActivityResponse {

    public UserActivityResponse(String sendDate, String text) {
        this.sendDate = sendDate;
        this.text = text;
    }

    private String sendDate;
    private String text;

    public String getSendDate() {
        return sendDate;
    }

    public void setSendDate(String sendDate) {
        this.sendDate = sendDate;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
