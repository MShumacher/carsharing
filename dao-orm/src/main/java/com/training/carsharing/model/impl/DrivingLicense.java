package com.training.carsharing.model.impl;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToOne;
import java.util.Date;

@Entity
@Getter
@Setter
public class DrivingLicense extends BaseEntity {

    @Column
    private String number;

    @Column
    private Date expirationDate;

    @Column
    private String categories;

    @OneToOne(fetch = FetchType.LAZY, optional = false, targetEntity = UserAccount.class)
    private UserAccount userAccount;

    @Override
    public String toString() {
        return "DrivingLicense{" + super.toString() +
                ", number='" + number + '\'' +
                ", expirationDate=" + expirationDate +
                ", categories='" + categories + '\'' +
                "}";
    }
}
