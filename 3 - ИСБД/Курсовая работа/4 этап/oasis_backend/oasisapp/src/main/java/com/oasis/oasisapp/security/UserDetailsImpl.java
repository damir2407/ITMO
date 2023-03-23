package com.oasis.oasisapp.security;

import com.fasterxml.jackson.annotation.JsonIgnore;

import com.oasis.oasisapp.model.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDate;
import java.time.OffsetDateTime;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;


public class UserDetailsImpl implements UserDetails {

    private static final long serialVersionUID = 1L;

    private String login;

    @JsonIgnore
    private String password;

    private String status;
    private String email;

    private LocalDate lastLoginDate;

    private LocalDate registrationDate;

    private Collection<? extends GrantedAuthority> authorities;

    public UserDetailsImpl(String login, String password, String status,
                           String email,
                           LocalDate lastLoginDate,
                           LocalDate registrationDate,
                           Collection<? extends GrantedAuthority> authorities) {
        this.login = login;
        this.password = password;
        this.status = status;
        this.email = email;
        this.lastLoginDate = lastLoginDate;
        this.registrationDate = registrationDate;
        this.authorities = authorities;
    }


    public static UserDetailsImpl build(User user) {

        List<GrantedAuthority> authorities = user.getRoles().stream()
                .map(roles -> new SimpleGrantedAuthority(roles.getName().name()))
                .collect(Collectors.toList());

        return new UserDetailsImpl(
                user.getLogin(),
                user.getPassword(),
                user.getStatus(),
                user.getEmail(),
                user.getLastLoginDate(),
                user.getRegistrationDate(),
                authorities);
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return null;
    }

    public String getStatus() {
        return status;
    }

    public String getEmail() {
        return email;
    }

    public LocalDate getLastLoginDate() {
        return lastLoginDate;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setLastLoginDate(LocalDate lastLoginDate) {
        this.lastLoginDate = lastLoginDate;
    }

    public void setAuthorities(Collection<? extends GrantedAuthority> authorities) {
        this.authorities = authorities;
    }

    public LocalDate getRegistrationDate() {
        return registrationDate;
    }

    public void setRegistrationDate(LocalDate registrationDate) {
        this.registrationDate = registrationDate;
    }
}
