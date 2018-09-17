package com.training.carsharing.model.impl;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import java.util.Date;

@Entity
@Getter
@Setter
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
