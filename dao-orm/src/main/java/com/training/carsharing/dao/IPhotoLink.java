package com.training.carsharing.dao;

public interface IPhotoLink extends IBaseEntity {

    IAd getAd();

    void setAd(IAd ad);

    String getLink();

    void setLink(String link);
}
