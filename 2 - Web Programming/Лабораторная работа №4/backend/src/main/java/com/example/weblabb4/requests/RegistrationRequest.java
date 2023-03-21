package com.example.weblabb4.requests;



import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;


public class RegistrationRequest {


    @Size(min=1, max=15, message = "Username should not be null or empty and exceed 15 characters")
    @NotBlank(message = "Username can't be NULL")
    private String username;

    @NotBlank(message = "Password can't be NULL")
    @Size(min=1, max=15, message = "Password should not be null or empty and exceed 15 characters")
    private String password;



    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
