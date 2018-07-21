package com.training.carsharing.model;

import java.util.Date;

public interface IDrivingLicense extends IBaseEntity {
    String getNumber();

    void setNumber(String number);

    Date getExpirationDate();

    void setExpirationDate(Date expirationDate);

    String getCategories();

    void setCategories(String categories);
}
