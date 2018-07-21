package com.training.carsharing.model.impl;

import com.training.carsharing.model.IDrivingLicense;

import javax.persistence.Column;
import javax.persistence.Entity;
import java.util.Date;

@Entity
public class DrivingLicense extends BaseEntity implements IDrivingLicense {

    @Column
    private String number;

    @Column
    private Date expirationDate;

    @Column
    private String categories;

    @Override
    public String getNumber() {
        return number;
    }

    @Override
    public void setNumber(String number) {
        this.number = number;
    }

    @Override
    public Date getExpirationDate() {
        return expirationDate;
    }

    @Override
    public void setExpirationDate(Date expirationDate) {
        this.expirationDate = expirationDate;
    }

    @Override
    public String getCategories() {
        return categories;
    }

    @Override
    public void setCategories(String categories) {
        this.categories = categories;
    }

    @Override
    public String toString() {
        return "DrivingLicense{" + super.toString() +
                ", number='" + number + '\'' +
                ", expirationDate=" + expirationDate +
                ", categories='" + categories + '\'' +
                "}";
    }
}
