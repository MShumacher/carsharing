package com.training.carsharing.model;

public interface ICarsPhoto extends IBaseEntity {

    ICar getCar();

    void setCar(ICar car);

    String getLink();

    void setLink(String link);
}
