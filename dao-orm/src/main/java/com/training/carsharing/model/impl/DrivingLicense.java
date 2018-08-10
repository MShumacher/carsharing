package com.training.carsharing.model.impl;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
public class DrivingLicense extends BaseEntity {

    @Column
    private String number;

    @Column
    private Date expirationDate;

    @Column
    private String categories;

    @OneToOne(fetch = FetchType.LAZY, optional = false, targetEntity = UserAccount.class)
    private UserAccount userAccount;

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public Date getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(Date expirationDate) {
        this.expirationDate = expirationDate;
    }

    public String getCategories() {
        return categories;
    }

    public void setCategories(String categories) {
        this.categories = categories;
    }

    public UserAccount getUserAccount() {
        return userAccount;
    }

    public void setUserAccount(final UserAccount userAccount) {
        this.userAccount = userAccount;
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
