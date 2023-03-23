package com.oasis.oasisapp.dto;


import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.Set;

public class SignUpRequest {

    @Size(min = 1, max = 10, message = "Укажите логин пользователя! От 1 до 10 символов")
    @NotBlank(message = "Укажите логин!")
    private String login;

    @Size(min = 6, max = 15, message = "Укажите пароль! От 6 до 15 символов")
    @NotBlank(message = "Укажите пароль!")
    private String password;

    @Email(message = "Неверный формат электронной почты!")
    @Size(min = 1, max = 64, message = "Укажите почту! От 1 до 64 символов")
    @NotBlank(message = "Укажите электронную почту!")
    private String email;

    public SignUpRequest(String login, String password, String email) {
        this.login = login;
        this.password = password;
        this.email = email;
    }


    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }


}
