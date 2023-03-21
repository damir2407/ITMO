package com.example.weblabb4.entity;


import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Comparator;
import java.util.Objects;

@Entity
@Data
public class PointEntity implements Serializable {
    @Id
    @SequenceGenerator(name = "pointSequence", sequenceName = "POINT_SEQUENCE", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "pointSequence")
    private Long id;
    private Double x;
    private Double y;
    private Double r;
    private String currentTime;
    private String executeTime;
    private Boolean hitValue;
    private String username;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public PointEntity() {
    }

    public PointEntity(Double x, Double y, Double r, String currentTime, String executeTime, boolean hitValue) {
        this.x = x;
        this.y = y;
        this.r = r;
        this.currentTime = currentTime;
        this.executeTime = executeTime;
        this.hitValue = hitValue;
    }
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Double getX() {
        return x;
    }

    public void setX(Double x) {
        this.x = x;
    }

    public Double getY() {
        return y;
    }

    public void setY(Double y) {
        this.y = y;
    }

    public Double getR() {
        return r;
    }

    public void setR(Double r) {
        this.r = r;
    }

    public String getCurrentTime() {
        return currentTime;
    }

    public void setCurrentTime(String currentTime) {
        this.currentTime = currentTime;
    }

    public String getExecuteTime() {
        return executeTime;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PointEntity that = (PointEntity) o;
        return hitValue == that.hitValue && Objects.equals(id, that.id) && Objects.equals(x, that.x) && Objects.equals(y, that.y) && Objects.equals(r, that.r) && Objects.equals(currentTime, that.currentTime) && Objects.equals(executeTime, that.executeTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, x, y, r, currentTime, executeTime, hitValue);
    }

    @Override
    public String toString() {
        return "PointEntity{" +
                "id=" + id +
                ", x=" + x +
                ", y=" + y +
                ", r=" + r +
                ", currentTime='" + currentTime + '\'' +
                ", executeTime='" + executeTime + '\'' +
                ", hitValue=" + hitValue +
                '}';
    }
}
