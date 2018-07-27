package com.training.carsharing.model.impl;

import com.training.carsharing.model.IGearbox;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity
public class Gearbox extends BaseEntity implements IGearbox {

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
        return "gearbox{" + super.toString() +
                ", name='" + name + '\'' +
                "}";
    }
}
