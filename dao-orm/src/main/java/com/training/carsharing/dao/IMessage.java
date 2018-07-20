package com.training.carsharing.dao;

public interface IMessage extends IBaseEntity {

    String getMessage();

    void setMessage(String message);

    IUserAccount getSender();

    void setSender(IUserAccount sender);

    IUserAccount getRecipient();

    void setRecipient(IUserAccount recipient);

    IAd getAd();

    void setAd(IAd ad);

    boolean isViewed();

    void setViewed(boolean viewed);
}
