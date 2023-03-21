package com.example.weblabb4.requests;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

public class PointRequest {
    @NotBlank(message = "X coordinate can't be NULL")
    @Min(value = -4, message = "X coordinate must be between [-4;4]")
    @Max(value = 4, message = "X coordinate must be between  [-4;4]")
    private String x;

    @Min(value = -3, message = "Y coordinate must be between [-3;3]")
    @Max(value = 3, message = "Y coordinate must be between  [-3;3]")
    @NotBlank(message = "Y coordinate can't be NULL")
    private String y;

    @Min(value = -4, message = "R coordinate must be between [-4;4]")
    @Max(value = 4, message = "R coordinate must be between  [-4;4]")
    @NotBlank(message = "R coordinate can't be NULL")
    private String r;


    public PointRequest() {
    }

    public PointRequest(String x, String y, String r) {
        this.x = x;
        this.y = y;
        this.r = r;
    }

    public String getX() {
        return x;
    }

    public void setX(String x) {
        this.x = x;
    }

    public String getY() {
        return y;
    }

    public void setY(String y) {
        this.y = y;
    }

    public String getR() {
        return r;
    }

    public void setR(String r) {
        this.r = r;
    }
}
