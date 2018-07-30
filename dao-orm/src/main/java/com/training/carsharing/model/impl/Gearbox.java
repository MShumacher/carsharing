package com.training.carsharing.model.impl;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity
public class Gearbox extends BaseEntity {

    @Column
    private String name;

    public String getName() {
        return name;
    }

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
