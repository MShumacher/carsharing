package com.training.carsharing.model;

public interface IMessage extends IBaseEntity {

    String getMessage();

    void setMessage(String message);

    IAd getAd();

    void setAd(IAd ad);

    IUserAccount getSender();

    void setSender(IUserAccount sender);

    IUserAccount getRecipient();

    void setRecipient(IUserAccount recipient);

    boolean isViewed();

    void setViewed(boolean viewed);
}
