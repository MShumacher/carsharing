package com.training.carsharing.model;

import com.training.carsharing.model.enums.*;

import java.util.Set;

public interface ICar extends IBaseEntity {

    IAd getAd();

    void setAd(IAd ad);

    Set<IParameter> getParameters();

    void setParameters(Set<IParameter> parameters);

//    IUserAccount getUserAccount();
//
//    void setUserAccount(IUserAccount userAccount);

    IModel getModel();

    void setModel(IModel model);

    Integer getYear();

    void setYear(Integer year);

    String getPlate();

    void setPlate(String plate);

    Integer getMileage();

    void setMileage(Integer mileage);

    Integer getSeats();

    void setSeats(Integer seats);

    Gearbox getGearbox();

    void setGearbox(Gearbox gearbox);

    BodyType getBodyType();

    void setBodyType(BodyType bodyType);

    Drive getDrive();

    void setDrive(Drive drive);

    EngineType getEngineType();

    void setEngineType(EngineType engineType);

    Fuel getFuel();

    void setFuel(Fuel fuel);

    Double getCharge();

    void setCharge(Double charge);

    String getConditions();

    void setConditions(String conditions);

    String getInsurance();

    void setInsurance(String insurance);
}
