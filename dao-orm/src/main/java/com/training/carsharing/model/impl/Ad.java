package com.training.carsharing.model.impl;

import com.training.carsharing.model.IAd;
import com.training.carsharing.model.IModel;
import com.training.carsharing.model.IUserAccount;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;

@Entity
public class Ad extends BaseEntity implements IAd {

    @ManyToOne(fetch = FetchType.LAZY, targetEntity = UserAccount.class)
    private IUserAccount userAccountId;

    @ManyToOne(fetch = FetchType.LAZY, targetEntity = Model.class)
    private IModel modelId;

    @Column
    private String address;

    @Column
    private Double price;

    @Column
    private String body;

    @Column
    private boolean active;

    @Override
    public IUserAccount getUserAccountId() {
        return userAccountId;
    }

    @Override
    public void setUserAccountId(IUserAccount userAccountId) {
        this.userAccountId = userAccountId;
    }

    @Override
    public IModel getModelId() {
        return modelId;
    }

    @Override
    public void setModelId(IModel modelId) {
        this.modelId = modelId;
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
    public String toString() {
        return "Ad{" + super.toString() +
                ", userAccountId=" + userAccountId +
                ", modelId=" + modelId +
                ", address='" + address + '\'' +
                ", price=" + price +
                ", body='" + body + '\'' +
                ", active=" + active +
                "}";
    }
}
