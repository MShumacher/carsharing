package com.training.carsharing.model;

public interface IAd extends IBaseEntity {
    IUserAccount getUserAccountId();

    void setUserAccountId(IUserAccount userAccountId);

    IModel getModelId();

    void setModelId(IModel modelId);

    String getAddress();

    void setAddress(String address);

    Double getPrice();

    void setPrice(Double price);

    String getBody();

    void setBody(String body);

    boolean isActive();

    void setActive(boolean active);
}
