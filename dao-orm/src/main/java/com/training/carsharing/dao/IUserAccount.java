package com.training.carsharing.dao;

public interface IUserAccount extends IBaseEntity {

    String getEmail();

    void setEmail(String email);

    String getPassword();

    void setPassword(String password);

    String getName();

    void setName(String name);

    String getCity();

    void setCity(String city);

    String getPhone();

    void setPhone(String phone);
}
