package com.oasis.oasisapp.dto;

public class GuidesResponse {
    private String userLogin;
    private String sendDate;
    private String guideText;
    private String gamePictureUrl;
    private String gameName;


    public GuidesResponse(String userLogin, String sendDate, String guideText, String gamePictureUrl, String gameName) {
        this.userLogin = userLogin;
        this.sendDate = sendDate;
        this.guideText = guideText;
        this.gamePictureUrl = gamePictureUrl;
        this.gameName = gameName;
    }

    public String getUserLogin() {
        return userLogin;
    }

    public void setUserLogin(String userLogin) {
        this.userLogin = userLogin;
    }

    public String getSendDate() {
        return sendDate;
    }

    public void setSendDate(String sendDate) {
        this.sendDate = sendDate;
    }

    public String getGuideText() {
        return guideText;
    }

    public void setGuideText(String guideText) {
        this.guideText = guideText;
    }

    public String getGamePictureUrl() {
        return gamePictureUrl;
    }

    public void setGamePictureUrl(String gamePictureUrl) {
        this.gamePictureUrl = gamePictureUrl;
    }

    public String getGameName() {
        return gameName;
    }

    public void setGameName(String gameName) {
        this.gameName = gameName;
    }
}
