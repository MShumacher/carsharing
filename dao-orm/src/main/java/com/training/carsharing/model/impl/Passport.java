package com.training.carsharing.model.impl;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
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

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getIssuePlace() {
        return issuePlace;
    }

    public void setIssuePlace(String issuePlace) {
        this.issuePlace = issuePlace;
    }

    public Date getIssueDate() {
        return issueDate;
    }

    public void setIssueDate(Date issueDate) {
        this.issueDate = issueDate;
    }

    public String getBirthPlace() {
        return birthPlace;
    }

    public void setBirthPlace(String birthPlace) {
        this.birthPlace = birthPlace;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public UserAccount getUserAccount() {
        return userAccount;
    }

    public void setUserAccount(final UserAccount userAccount) {
        this.userAccount = userAccount;
    }

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
