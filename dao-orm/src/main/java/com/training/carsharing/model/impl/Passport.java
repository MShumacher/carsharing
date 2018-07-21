package com.training.carsharing.model.impl;

import com.training.carsharing.model.IPassport;

import javax.persistence.Column;
import javax.persistence.Entity;
import java.util.Date;

@Entity
public class Passport extends BaseEntity implements IPassport {

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

    @Override
    public String getFullName() {
        return fullName;
    }

    @Override
    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    @Override
    public String getNumber() {
        return number;
    }

    @Override
    public void setNumber(String number) {
        this.number = number;
    }

    @Override
    public String getIssuePlace() {
        return issuePlace;
    }

    @Override
    public void setIssuePlace(String issuePlace) {
        this.issuePlace = issuePlace;
    }

    @Override
    public Date getIssueDate() {
        return issueDate;
    }

    @Override
    public void setIssueDate(Date issueDate) {
        this.issueDate = issueDate;
    }

    @Override
    public String getBirthPlace() {
        return birthPlace;
    }

    @Override
    public void setBirthPlace(String birthPlace) {
        this.birthPlace = birthPlace;
    }

    @Override
    public Date getBirthday() {
        return birthday;
    }

    @Override
    public void setBirthday(Date birthday) {
        this.birthday = birthday;
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
