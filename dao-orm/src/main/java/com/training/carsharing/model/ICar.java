package com.training.carsharing.model;

import java.util.Set;

public interface ICar extends IBaseEntity {
    IUserAccount getUserAccount();

    void setUserAccount(IUserAccount userAccount);

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

    Double getCharge();

    void setCharge(Double charge);

    String getConditions();

    void setConditions(String conditions);

    String getInsurance();

    void setInsurance(String insurance);

    Set<IParameter> getParameters();

    void setParameters(Set<IParameter> parameters);
}
