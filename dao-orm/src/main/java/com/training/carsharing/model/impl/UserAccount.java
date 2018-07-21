package com.training.carsharing.model.impl;

import com.training.carsharing.model.IUserAccount;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.Objects;

@Entity
public class UserAccount extends BaseEntity implements IUserAccount {

    @Column
    private String email;

    @Column
    private String password;

    @Column
    private String name;

    private String photoLink;

    @Column
    private String phone;

    @Column
    private String role;

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
    public String toString() {
        return "UserAccount{" + super.toString() +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", name='" + name + '\'' +
                ", photoLink='" + photoLink + '\'' +
                ", phone='" + phone + '\'' +
                ", role='" + role + '\'' +
                "} " + super.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserAccount that = (UserAccount) o;
        return Objects.equals(email, that.email) &&
                Objects.equals(password, that.password) &&
                Objects.equals(name, that.name) &&
                Objects.equals(photoLink, that.photoLink) &&
                Objects.equals(phone, that.phone) &&
                Objects.equals(role, that.role);
    }

    @Override
    public int hashCode() {

        return Objects.hash(email, password, name, photoLink, phone, role);
    }
}
