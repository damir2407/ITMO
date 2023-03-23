package com.oasis.oasisapp.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class ActivityRequest {

    @NotBlank(message = "Сообщение не может быть пустым!")
    @Size(min = 1, max = 255, message = "Укажите сообщение! От 1 до 255 символов")
    private String text;

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
