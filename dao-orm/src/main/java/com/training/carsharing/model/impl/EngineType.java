package com.training.carsharing.model.impl;

import com.training.carsharing.model.IFuel;
import com.training.carsharing.model.IEngineType;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;

@Entity
public class EngineType extends BaseEntity implements IEngineType {

    @Column
    private String name;

    @ManyToOne(fetch = FetchType.LAZY, targetEntity = Fuel.class)
    private IFuel fuel;

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public IFuel getFuel() {
        return fuel;
    }

    @Override
    public void setFuel(IFuel fuel) {
        this.fuel = fuel;
    }

    @Override
    public String toString() {
        return "engineType{" + super.toString() +
                ", name='" + name + '\'' +
                ", fuelId='" + fuel.getId() + '\'' +
                "}";
    }
}
