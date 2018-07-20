package com.training.carsharing.dao.impl;

import com.training.carsharing.dao.IModel;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity
public class Model extends BaseEntity implements IModel {

    @Column
    private String name;

    @Column
    private String brand;

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String getBrand() {
        return brand;
    }

    @Override
    public void setBrand(String brand) {
        this.brand = brand;
    }

    @Override
    public String toString() {
        return "Model{" +
                "id='" + getId() + '\'' +
                ", name='" + name + '\'' +
                ", brand='" + brand + '\'' +
                '}';
    }
}
