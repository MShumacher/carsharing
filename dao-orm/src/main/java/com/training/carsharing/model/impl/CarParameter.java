package com.training.carsharing.model.impl;

import com.training.carsharing.model.ICarParameter;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity
public class CarParameter extends BaseEntity implements ICarParameter {

    @Column
    private String brand;

    @Column
    private String gearbox;

    @Column
    private String bodyType;

    @Column
    private String drive;

    @Column
    private String engineType;

    @Column
    private String fuel;

    @Override
    public String getBrand() {
        return brand;
    }

    @Override
    public void setBrand(String brand) {
        this.brand = brand;
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
    public String getBodyType() {
        return bodyType;
    }

    @Override
    public void setBodyType(String bodyType) {
        this.bodyType = bodyType;
    }

    @Override
    public String getDrive() {
        return drive;
    }

    @Override
    public void setDrive(String drive) {
        this.drive = drive;
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
    public String getFuel() {
        return fuel;
    }

    @Override
    public void setFuel(String fuel) {
        this.fuel = fuel;
    }

    @Override
    public String toString() {
        return "CarParameter{" + super.toString() +
                ", brand='" + brand + '\'' +
                ", gearbox='" + gearbox + '\'' +
                ", bodyType='" + bodyType + '\'' +
                ", drive='" + drive + '\'' +
                ", engineType='" + engineType + '\'' +
                ", fuel='" + fuel + '\'' +
                "}";
    }
}
