package com.training.carsharing.model.impl;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import javax.persistence.*;
import java.util.Date;

@Entity
public class Ad {

    public static int DEFAULT_VERSION = 1;

    @GenericGenerator(name = "generator", strategy = "foreign", parameters = @Parameter(name = "property", value = "car"))
    @Id
    @GeneratedValue(generator = "generator")
    private Integer id;

    @OneToOne(fetch = FetchType.EAGER, optional = false, targetEntity = Car.class)
    @PrimaryKeyJoinColumn
    private Car car;

    @ManyToOne(fetch = FetchType.LAZY, targetEntity = UserAccount.class)
    private UserAccount userAccount;

    @Column
    private String address;

    @Column
    private Double price;

    @Column
    private String body;

    @Column
    private boolean active;

    @Column
    @Version
    private Integer version;

    @Column(updatable = false)
    private Long created;

    @Column
    private Long updated;

    public Integer getId() {
        return id;
    }

    public void setId(final Integer id) {
        this.id = id;
    }

    public UserAccount getUserAccount() {
        return userAccount;
    }

    public void setUserAccount(UserAccount userAccount) {
        this.userAccount = userAccount;
    }

    public Car getCar() {
        return car;
    }

    public void setCar(Car car) {
        this.car = car;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public Integer getVersion() {
        return version;
    }

    public void setVersion(final Integer version) {
        this.version = version;
    }

    public Date getCreated() {
        return new Date(created);
    }

    public void setCreated(final Date created) {
        this.created = created.getTime();
    }

    public Date getUpdated() {
        return new Date(updated);
    }

    public void setUpdated(final Date updated) {
        this.updated = updated.getTime();
    }

    @Override
    public String toString() {
        return "Ad{" +
                "id=" + id +
                super.toString() +
                ", carId=" + car.getId() +
                ", userAccountId=" + userAccount.getId() +
                ", address='" + address + '\'' +
                ", price=" + price +
                ", body='" + body + '\'' +
                ", active=" + active +
                '}';
    }
}
