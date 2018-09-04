package com.training.carsharing.model.impl;

import com.training.carsharing.model.enums.Role;

import javax.persistence.*;

@Entity
public class UserAccount extends BaseEntity {

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
    @Enumerated(EnumType.STRING)
    private Role role;

    @Column
    private String verifyKey;

    @Column
    private boolean verified;

    @OneToOne(fetch = FetchType.LAZY, mappedBy = "userAccount", targetEntity = Passport.class)
    private Passport passport;

    @OneToOne(fetch = FetchType.LAZY, mappedBy = "userAccount", targetEntity = DrivingLicense.class)
    private DrivingLicense drivingLicense;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhotoLink() {
        return photoLink;
    }

    public void setPhotoLink(String photoLink) {
        this.photoLink = photoLink;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public String getVerifyKey() {
        return verifyKey;
    }

    public void setVerifyKey(String verifyKey) {
        this.verifyKey = verifyKey;
    }

    public boolean isVerified() {
        return verified;
    }

    public void setVerified(boolean verified) {
        this.verified = verified;
    }

    public Passport getPassport() {
        return passport;
    }

    public void setPassport(final Passport passport) {
        this.passport = passport;
    }

    public DrivingLicense getDrivingLicense() {
        return drivingLicense;
    }

    public void setDrivingLicense(final DrivingLicense drivingLicense) {
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
                ", verifyKey='" + verifyKey + '\'' +
                ", verified='" + verified + '\'' +
                "}";
    }
}
