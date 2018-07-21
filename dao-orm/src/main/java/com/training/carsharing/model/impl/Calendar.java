package com.training.carsharing.model.impl;

import com.training.carsharing.model.ICalendar;
import com.training.carsharing.model.IUserAccount;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import java.util.Date;

@Entity
public class Calendar extends BaseEntity implements ICalendar {

    @Column
    private String message;

    @ManyToOne(fetch = FetchType.LAZY, targetEntity = UserAccount.class)
    private IUserAccount renter;

    @ManyToOne(fetch = FetchType.LAZY, targetEntity = Car.class)
    private IUserAccount car;

    @Column
    private Date start;

    @Column
    private Date end;

    @Column
    private Double totalPrice;

    @Override
    public String getMessage() {
        return message;
    }

    @Override
    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public IUserAccount getRenter() {
        return renter;
    }

    @Override
    public void setRenter(IUserAccount renter) {
        this.renter = renter;
    }

    @Override
    public IUserAccount getCar() {
        return car;
    }

    @Override
    public void setCar(IUserAccount car) {
        this.car = car;
    }

    @Override
    public Date getStart() {
        return start;
    }

    @Override
    public void setStart(Date start) {
        this.start = start;
    }

    @Override
    public Date getEnd() {
        return end;
    }

    @Override
    public void setEnd(Date end) {
        this.end = end;
    }

    @Override
    public Double getTotalPrice() {
        return totalPrice;
    }

    @Override
    public void setTotalPrice(Double totalPrice) {
        this.totalPrice = totalPrice;
    }

    @Override
    public String toString() {
        return "Calendar{" + super.toString() +
                ", message='" + message + '\'' +
                ", renter=" + renter.getName() +
                ", car=" + car.getName() +
                ", start=" + start +
                ", end=" + end +
                ", totalPrice=" + totalPrice +
                "}";
    }
}
