package com.training.carsharing.model.impl;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import java.util.Date;

@Entity
public class Calendar extends BaseEntity {

    @ManyToOne(fetch = FetchType.LAZY, targetEntity = UserAccount.class)
    private UserAccount renter;

    @ManyToOne(fetch = FetchType.LAZY, targetEntity = Car.class)
    private Car car;

    @Column
    private Date start;

    @Column
    private Date end;

    @Column
    private Double totalPrice;

    public UserAccount getRenter() {
        return renter;
    }

    public void setRenter(UserAccount renter) {
        this.renter = renter;
    }

    public Car getCar() {
        return car;
    }

    public void setCar(Car car) {
        this.car = car;
    }

    public Date getStart() {
        return start;
    }

    public void setStart(Date start) {
        this.start = start;
    }

    public Date getEnd() {
        return end;
    }

    public void setEnd(Date end) {
        this.end = end;
    }

    public Double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(Double totalPrice) {
        this.totalPrice = totalPrice;
    }

    @Override
    public String toString() {
        return "Calendar{" + super.toString() +
                ", renterId=" + renter.getId() +
                ", carId=" + car.getId() +
                ", start=" + start +
                ", end=" + end +
                ", totalPrice=" + totalPrice +
                "}";
    }
}
