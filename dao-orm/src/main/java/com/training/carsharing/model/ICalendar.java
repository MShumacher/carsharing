package com.training.carsharing.model;

import java.util.Date;

public interface ICalendar extends IBaseEntity {
    String getMessage();

    void setMessage(String message);

    IUserAccount getRenter();

    void setRenter(IUserAccount renter);

    IUserAccount getCar();

    void setCar(IUserAccount car);

    Date getStart();

    void setStart(Date start);

    Date getEnd();

    void setEnd(Date end);

    Double getTotalPrice();

    void setTotalPrice(Double totalPrice);
}
