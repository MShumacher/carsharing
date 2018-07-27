package com.training.carsharing.model.impl;

import com.training.carsharing.model.IBrand;
import com.training.carsharing.model.IModel;

import javax.persistence.*;
import java.util.Objects;

@Entity
public class Model extends BaseEntity implements IModel {

    @Column
    private String name;

    @ManyToOne(fetch = FetchType.LAZY, targetEntity = Brand.class)
    private IBrand brand;

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public IBrand getBrand() {
        return brand;
    }

    @Override
    public void setBrand(IBrand brand) {
        this.brand = brand;
    }

    @Override
    public String toString() {
        return "model{" + super.toString() +
                ", name='" + name + '\'' +
                ", brandId='" + brand.getId() + '\'' +
                "}";
    }
}
