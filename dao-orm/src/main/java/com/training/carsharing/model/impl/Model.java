package com.training.carsharing.model.impl;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;

@Entity
public class Model extends BaseEntity {

    @Column
    private String name;

    @ManyToOne(fetch = FetchType.LAZY, targetEntity = Brand.class)
    private Brand brand;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Brand getBrand() {
        return brand;
    }

    public void setBrand(Brand brand) {
        this.brand = brand;
    }

    @Override
    public String toString() {
        return "model{" + super.toString() + ", name='" + name + '\'' + ", brandId=" + brand.getId() + "}";
    }
}
