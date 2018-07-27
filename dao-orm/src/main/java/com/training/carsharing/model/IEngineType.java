package com.training.carsharing.model;

public interface IEngineType extends IBaseEntity {

    String getName();

    void setName(String name);

    IFuel getFuel();

    void setFuel(IFuel fuel);
}
