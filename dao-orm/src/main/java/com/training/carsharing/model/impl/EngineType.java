package com.training.carsharing.model.impl;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;

@Entity
public class EngineType extends BaseEntity {

    @Column
    private String name;

    @ManyToOne(fetch = FetchType.LAZY, targetEntity = Fuel.class)
    private Fuel fuel;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Fuel getFuel() {
        return fuel;
    }

    public void setFuel(Fuel fuel) {
        this.fuel = fuel;
    }

    @Override
    public String toString() {
        return "engineType{" + super.toString() +
                ", name='" + name + '\'' +
                ", fuelId=" + fuel.getId() +
                "}";
    }
}
