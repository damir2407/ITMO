package com.example.weblab3.server;


import javax.persistence.*;

@Entity
@Table(name = "Resulted")
public class Resulted {
    private Double x;
    private Double y;
    private Double r;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String currentTime;
    private String executeTime;
    private boolean hitValue;


    public Resulted(Double x, Double y, Double r, String currentTime, String executeTime, boolean hitValue) {
        this.x = x;
        this.y = y;
        this.r = r;
        this.currentTime = currentTime;
        this.executeTime = executeTime;
        this.hitValue = hitValue;
    }


    public Resulted() {
    }

    public void setX(Double x) {
        this.x = x;
    }

    public void setY(Double y) {
        this.y = y;
    }

    public void setR(Double r) {
        this.r = r;
    }

    public Double getX() {
        return x;
    }

    public Double getY() {
        return y;
    }

    public Double getR() {
        return r;
    }

    public String getCurrentTime() {
        return currentTime;
    }

    public String getExecuteTime() {
        return executeTime;
    }

    public boolean isHitValue() {
        return hitValue;
    }

    public void setCurrentTime(String currentTime) {
        this.currentTime = currentTime;
    }

    public void setExecuteTime(String executeTime) {
        this.executeTime = executeTime;
    }

    public void setHitValue(boolean hitValue) {
        this.hitValue = hitValue;
    }

    @Override
    public String toString() {
        return "Result{" +
                "x=" + x +
                ", y=" + y +
                ", r=" + r +
                ", currentTime='" + currentTime + '\'' +
                ", executeTime='" + executeTime + '\'' +
                ", hitValue=" + hitValue +
                '}';
    }
}
