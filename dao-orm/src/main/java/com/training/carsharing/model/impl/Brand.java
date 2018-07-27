package com.training.carsharing.model.impl;

import com.training.carsharing.model.IBrand;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity
public class Brand extends BaseEntity implements IBrand {

    @Column

    private String name;


    @Override
    public String getName() {
        return name;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "brand{" + super.toString() +
                ", name='" + name + '\'' +
                "}";
    }
}
