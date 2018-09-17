package com.training.carsharing.model.impl;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
public class Ad extends BaseEntity {

    @OneToOne(fetch = FetchType.LAZY, optional = false, targetEntity = Car.class)
    private Car car;

    @ManyToOne(fetch = FetchType.LAZY, targetEntity = UserAccount.class)
    private UserAccount userAccount;

    @Column
    private String address;

    @Column
    private Double price;

    @Column
    private String body;

    @Column
    private boolean active;

    @Override
    public String toString() {
        return "Ad{" +
                super.toString() +
                ", carId=" + car.getId() +
                ", userAccountId=" + userAccount.getId() +
                ", address='" + address + '\'' +
                ", price=" + price +
                ", body='" + body + '\'' +
                ", active=" + active +
                '}';
    }
}
