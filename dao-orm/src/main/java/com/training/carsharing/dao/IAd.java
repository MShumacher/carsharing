package com.training.carsharing.dao;

import java.util.Set;

public interface IAd extends IBaseEntity{

IUserAccount getUserAccountId();

void setUserAccountId(IUserAccount userAccountId);

IModel getModelId();

void setModelId(IModel modelId);

String getEngineType();

void setEngineType(String engineType);

Integer getEngineVolume();

void setEngineVolume(Integer engineVolume);

String getGearbox();

void setGearbox(String gearbox);

Integer getYear();

void setYear(Integer year);

Integer getMileage();

void setMileage(Integer mileage);

String getColor();

void setColor(String color);

Integer getPrice();

void setPrice(Integer price);

boolean isBarter();

void setBarter(boolean barter);

boolean isActive();

void setActive(boolean active);

    Set<IParameter> getEngines();

    void setEngines(Set<IParameter> engines);
}
