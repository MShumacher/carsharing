package com.training.carsharing.model.impl;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;

@Entity
@Getter
@Setter
public class EngineType extends BaseEntity {

    @Column
    private String name;

    @ManyToOne(fetch = FetchType.LAZY, targetEntity = Fuel.class)
    private Fuel fuel;

    @Override
    public String toString() {
        return "engineType{" + super.toString() +
                ", name='" + name + '\'' +
                ", fuelId=" + fuel.getId() +
                "}";
    }
}
