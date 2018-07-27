package com.training.carsharing.model.impl;

import com.training.carsharing.model.IDrive;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity
public class Drive extends BaseEntity implements IDrive {

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
        return "drive{" + super.toString() +
                ", name='" + name + '\'' +
                "}";
    }
}
