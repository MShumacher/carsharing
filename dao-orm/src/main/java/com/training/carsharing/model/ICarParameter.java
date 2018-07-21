package com.training.carsharing.model;

public interface ICarParameter extends IBaseEntity {
    String getBrand();

    void setBrand(String brand);

    String getGearbox();

    void setGearbox(String gearbox);

    String getBodyType();

    void setBodyType(String bodyType);

    String getDrive();

    void setDrive(String drive);

    String getEngineType();

    void setEngineType(String engineType);

    String getFuel();

    void setFuel(String fuel);
}
