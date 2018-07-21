package com.training.carsharing.model.impl;

import com.training.carsharing.model.IModel;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name="model")
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
        return "model{" + super.toString() +
                ", name='" + name + '\'' +
                ", brand='" + brand + '\'' +
                "}";
    }
}
