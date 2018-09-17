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
public class CarsPhoto extends BaseEntity {

    @ManyToOne(fetch = FetchType.LAZY, targetEntity = Car.class)
    private Car car;

    @Column
    private String link;

    @Override
    public String toString() {
        return "CarsPhoto{" + super.toString() +
                ", carId=" + car.getId() +
                ", link='" + link + '\'' +
                "}";
    }
}
