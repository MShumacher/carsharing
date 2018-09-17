package com.training.carsharing.model.impl;

import com.training.carsharing.model.enums.Role;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
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
