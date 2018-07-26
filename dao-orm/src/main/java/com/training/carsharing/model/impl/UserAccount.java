package com.training.carsharing.model.impl;

import com.training.carsharing.model.IDrivingLicense;
import com.training.carsharing.model.IPassport;
import com.training.carsharing.model.IUserAccount;

import javax.persistence.*;
import java.util.Objects;

@Entity
public class UserAccount extends BaseEntity implements IUserAccount {

    @Column
    private String email;

    @Column
    private String password;

    @Column
    private String name;

    @Column
    private String photoLink;

    @Column
    private String phone;

    @Column
    private String role;

    @OneToOne(fetch = FetchType.LAZY, mappedBy = "userAccount", targetEntity = Passport.class)
    private IPassport passport;

    @OneToOne(fetch = FetchType.LAZY, mappedBy = "userAccount", targetEntity = DrivingLicense.class)
    private IDrivingLicense drivingLicense;

    @Override
    public String getEmail() {
        return email;
    }

    @Override
    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String getName() { return name; }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String getPhotoLink() {
        return photoLink;
    }

    @Override
    public void setPhotoLink(String photoLink) {
        this.photoLink = photoLink;
    }

    @Override
    public String getPhone() {
        return phone;
    }

    @Override
    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Override
    public String getRole() {
        return role;
    }

    @Override
    public void setRole(String role) {
        this.role = role;
    }

    @Override
    public IPassport getPassport() {
        return passport;
    }

    @Override
    public void setPassport(final IPassport passport) {
        this.passport = passport;
    }

    @Override
    public IDrivingLicense getDrivingLicense() {
        return drivingLicense;
    }

    @Override
    public void setDrivingLicense(final IDrivingLicense drivingLicense) {
        this.drivingLicense = drivingLicense;
    }

    @Override
    public String toString() {
        return "UserAccount{" + super.toString() +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", name='" + name + '\'' +
                ", photoLink='" + photoLink + '\'' +
                ", phone='" + phone + '\'' +
                ", role='" + role + '\'' +
                "}";
    }
}
