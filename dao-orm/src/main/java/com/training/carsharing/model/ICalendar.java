package com.training.carsharing.model;

import java.util.Date;

public interface ICalendar extends IBaseEntity {

    IUserAccount getRenter();

    void setRenter(IUserAccount renter);

    ICar getCar();

    void setCar(ICar car);

    Date getStart();

    void setStart(Date start);

    Date getEnd();

    void setEnd(Date end);

    Double getTotalPrice();

    void setTotalPrice(Double totalPrice);
}
