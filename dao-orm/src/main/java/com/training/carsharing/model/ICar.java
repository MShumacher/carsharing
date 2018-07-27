package com.training.carsharing.model;

import java.util.Set;

public interface ICar extends IBaseEntity {

    IAd getAd();

    void setAd(IAd ad);

//    IUserAccount getUserAccount();
//
//    void setUserAccount(IUserAccount userAccount);

    Set<ICarParameter> getCarParameter();

    void setCarParameter(Set<ICarParameter> carParameter);

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

    IGearbox getGearbox();

    void setGearbox(IGearbox gearbox);

    IBodyType getBodyType();

    void setBodyType(IBodyType bodyType);

    IDrive getDrive();

    void setDrive(IDrive drive);

    IEngineType getEngineType();

    void setEngineType(IEngineType engineType);

    Double getCharge();

    void setCharge(Double charge);

    String getConditions();

    void setConditions(String conditions);

    String getInsurance();

    void setInsurance(String insurance);
}
