package com.training.carsharing.model.impl;

import com.training.carsharing.model.IBodyType;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity
public class BodyType extends BaseEntity implements IBodyType {

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
        return "bodyType{" + super.toString() +
                ", name='" + name + '\'' +
                "}";
    }
}
