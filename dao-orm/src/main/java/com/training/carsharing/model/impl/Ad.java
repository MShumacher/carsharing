package com.training.carsharing.model.impl;

import com.training.carsharing.model.IAd;
import com.training.carsharing.model.ICar;
import com.training.carsharing.model.IUserAccount;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import javax.persistence.*;
import java.util.Date;

@Entity
public class Ad implements IAd {

    @GenericGenerator(name = "generator", strategy = "foreign", parameters = @Parameter(name = "property", value = "car"))
    @Id
    @GeneratedValue(generator = "generator")
//    @Id
//    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Integer id;

    @OneToOne(fetch = FetchType.EAGER, optional = false, targetEntity = Car.class)
    @PrimaryKeyJoinColumn
    private ICar car;

    @ManyToOne(fetch = FetchType.LAZY, targetEntity = UserAccount.class)
    private IUserAccount userAccount;

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

    @Override
    public Integer getId() {
        return id;
    }

    @Override
    public void setId(final Integer id) {
        this.id = id;
    }

    @Override
    public IUserAccount getUserAccount() {
        return userAccount;
    }

    @Override
    public void setUserAccount(IUserAccount userAccount) {
        this.userAccount = userAccount;
    }

    @Override
    public ICar getCar() {
        return car;
    }

    @Override
    public void setCar(ICar car) {
        this.car = car;
    }

    @Override
    public String getAddress() {
        return address;
    }

    @Override
    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public Double getPrice() {
        return price;
    }

    @Override
    public void setPrice(Double price) {
        this.price = price;
    }

    @Override
    public String getBody() {
        return body;
    }

    @Override
    public void setBody(String body) {
        this.body = body;
    }

    @Override
    public boolean isActive() {
        return active;
    }

    @Override
    public void setActive(boolean active) {
        this.active = active;
    }

    @Override
    public Integer getVersion() {
        return version;
    }

    @Override
    public void setVersion(final Integer version) {
        this.version = version;
    }

    @Override
    public Date getCreated() {
        return new Date(created);
    }

    @Override
    public void setCreated(final Date created) {
        this.created = created.getTime();
    }

    @Override
    public Date getUpdated() {
        return new Date(updated);
    }

    @Override
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
