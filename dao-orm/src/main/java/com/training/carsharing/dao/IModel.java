package com.training.carsharing.dao;

public interface IModel extends IBaseEntity {

    String getName();

    void setName(String name);

    String getBrand();

    void setBrand(String brand);
}
