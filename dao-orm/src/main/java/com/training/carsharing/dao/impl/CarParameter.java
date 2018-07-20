package com.training.carsharing.dao.impl;

import com.training.carsharing.dao.ICarParameter;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity
public class CarParameter extends BaseEntity implements ICarParameter {

    @Column
    private String brand;

    @Column
    private String engineType;

    @Column
    private String gearbox;

    @Column
    private String color;

    @Override
    public String getBrand() {
        return brand;
    }

    @Override
    public void setBrand(String brand) {
        this.brand = brand;
    }

    @Override
    public String getEngineType() {
        return engineType;
    }

    @Override
    public void setEngineType(String engineType) {
        this.engineType = engineType;
    }

    @Override
    public String getGearbox() {
        return gearbox;
    }

    @Override
    public void setGearbox(String gearbox) {
        this.gearbox = gearbox;
    }

    @Override
    public String getColor() {
        return color;
    }

    @Override
    public void setColor(String color) {
        this.color = color;
    }

    @Override
    public String toString() {
        return "CarParameter{" +
                "id='" + getId() + '\'' +
                ", brand='" + brand + '\'' +
                ", engineType='" + engineType + '\'' +
                ", gearbox='" + gearbox + '\'' +
                ", color='" + color + '\'' +
                '}';
    }
}
