package com.training.carsharing.model.impl;

import com.training.carsharing.model.ICar;
import com.training.carsharing.model.ICarsPhoto;

import javax.persistence.*;

@Entity
@Table(name="cars_photo")
public class CarsPhoto extends BaseEntity implements ICarsPhoto {

    @ManyToOne(fetch = FetchType.LAZY, targetEntity = Car.class)
    private ICar car;

    @Column
    private String link;

    @Override
    public ICar getCar() {
        return car;
    }

    @Override
    public void setCar(ICar car) {
        this.car = car;
    }

    @Override
    public String getLink() {
        return link;
    }

    @Override
    public void setLink(String link) {
        this.link = link;
    }

    @Override
    public String toString() {
        return "CarsPhoto{" + super.toString() +
//                ", car=" + car.getModel() +
                ", link='" + link + '\'' +
                "}";
    }
}
