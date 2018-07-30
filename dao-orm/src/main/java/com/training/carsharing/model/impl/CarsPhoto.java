package com.training.carsharing.model.impl;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;

@Entity
public class CarsPhoto extends BaseEntity {

    @ManyToOne(fetch = FetchType.LAZY, targetEntity = Car.class)
    private Car car;

    @Column
    private String link;

    public Car getCar() {
        return car;
    }

    public void setCar(Car car) {
        this.car = car;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    @Override
    public String toString() {
        return "CarsPhoto{" + super.toString() +
                ", carId=" + car.getId() +
                ", link='" + link + '\'' +
                "}";
    }
}
