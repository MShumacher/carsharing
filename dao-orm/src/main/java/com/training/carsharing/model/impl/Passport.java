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
public class Passport extends BaseEntity {

    @Column
    private String fullName;

    @Column
    private String number;

    @Column
    private String issuePlace;

    @Column
    private Date issueDate;

    @Column
    private String birthPlace;

    @Column
    private Date birthday;

    @OneToOne(fetch = FetchType.LAZY, optional = false, targetEntity = UserAccount.class)
    private UserAccount userAccount;

    @Override
    public String toString() {
        return "Passport{" + super.toString() +
                ", fullName='" + fullName + '\'' +
                ", number='" + number + '\'' +
                ", issuePlace='" + issuePlace + '\'' +
                ", issueDate=" + issueDate +
                ", birthPlace='" + birthPlace + '\'' +
                ", birthday=" + birthday +
                "} ";
    }
}
