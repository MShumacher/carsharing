package com.training.carsharing.model;

public interface IModel extends IBaseEntity {

    String getName();

    void setName(String name);

    IBrand getBrand();

    void setBrand(IBrand brand);
}
