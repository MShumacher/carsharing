package com.training.carsharing.model.impl;

import com.training.carsharing.model.IParameter;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity
public class CarParameter extends BaseEntity implements IParameter {

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
        return "CarParameter{" + super.toString() +
                ", name='" + name + '\'' +
                "}";
    }
}
