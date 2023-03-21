package com.example.weblabb4.entity;

import javax.persistence.*;
import java.io.Serializable;

@Entity
public class RoleEntity implements Serializable {

    @Id
    private Integer id = 1;

    private String name = "ROLE_USER";


    public RoleEntity() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
