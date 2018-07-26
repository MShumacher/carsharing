package com.training.carsharing.model;

public interface IAd extends IBaseEntity {

    IUserAccount getUserAccount();

    void setUserAccount(IUserAccount userAccount);

    ICar getCar();

    void setCar(ICar car);

    String getAddress();

    void setAddress(String address);

    Double getPrice();

    void setPrice(Double price);

    String getBody();

    void setBody(String body);

    boolean isActive();

    void setActive(boolean active);

}
