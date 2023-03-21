package com.example.webblab2.savers;

public class Result {
    private double x;
    private double y;
    private double r;
    private String currentTime;
    private String executeTime;
    private boolean hitValue;


    public Result(double x, double y, double r, String currentTime, String executeTime, boolean hitValue) {
        this.x = x;
        this.y = y;
        this.r = r;
        this.currentTime = currentTime;
        this.executeTime = executeTime;
        this.hitValue = hitValue;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public double getR() {
        return r;
    }

    public String getCurrentTime() {
        return currentTime;
    }

    public String getExecuteTime() {
        return executeTime;
    }

    public void setX(double x) {
        this.x = x;
    }

    public void setY(double y) {
        this.y = y;
    }

    public void setR(double r) {
        this.r = r;
    }

    public void setCurrentTime(String currentTime) {
        this.currentTime = currentTime;
    }

    public void setExecuteTime(String executeTime) {
        this.executeTime = executeTime;
    }

    public boolean isHitValue() {
        return hitValue;
    }

    public void setHitValue(boolean hitValue) {
        this.hitValue = hitValue;
    }


}
