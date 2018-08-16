package com.training.carsharing.model.impl;

import javax.persistence.*;

@Entity
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

    public UserAccount getUserAccount() {
        return userAccount;
    }

    public void setUserAccount(UserAccount userAccount) {
        this.userAccount = userAccount;
    }

    public Car getCar() {
        return car;
    }

    public void setCar(Car car) {
        this.car = car;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

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
