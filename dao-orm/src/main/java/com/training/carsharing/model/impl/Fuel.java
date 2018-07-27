package com.training.carsharing.model.impl;

import com.training.carsharing.model.IFuel;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity
public class Fuel extends BaseEntity implements IFuel {

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
        return "fuel{" + super.toString() +
                ", name='" + name + '\'' +
                "}";
    }
}
