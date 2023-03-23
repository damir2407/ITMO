package com.oasis.oasisapp.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class GuideRequest {

    @NotBlank(message = "Выберите игру для руководства!")
    @NotNull(message = "Выберите игру для руководства!")
    private String gameName;

    @NotBlank(message = "Обзор не может быть пустым!")
    @Size(min = 1, max = 1000, message = "Укажите обзор! От 1 до 1000 символов")
    private String guideText;

    public String getGameName() {
        return gameName;
    }

    public void setGameName(String gameName) {
        this.gameName = gameName;
    }

    public String getGuideText() {
        return guideText;
    }

    public void setGuideText(String guideText) {
        this.guideText = guideText;
    }
}
