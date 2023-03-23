package com.oasis.oasisapp.model;

import java.util.HashSet;
import java.util.Set;

public class Dev {
    private String login;

    private String password;
    private String email;
    private Set<String> role;


    public Dev(String login, String password, String email) {
        this.login = login;
        this.password = password;
        this.email = email;
        this.role = new HashSet<>();
        this.role.add("DEV");
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Set<String> getRole() {
        return role;
    }

    public void setRole(Set<String> role) {
        this.role = role;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
