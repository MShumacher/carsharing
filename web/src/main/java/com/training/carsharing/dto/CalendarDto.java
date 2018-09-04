package com.training.carsharing.dto;

import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.*;
import java.util.Date;

public class CalendarDto extends BaseDto {

    @NotNull
    private Long renterId;

    private String renterEmail;

    @NotNull
    private Long carId;

    private String carPlate;

    //@Future
    @DateTimeFormat(pattern = "dd.MM.yyyy")
    @NotNull
    private Date start;

    //@Future
    @DateTimeFormat(pattern = "dd.MM.yyyy")
    @NotNull
    private Date end;

    @Min(0)
    @Digits(integer = 10, fraction = 2) // валидация на 2 знака после запятой
    @NotNull
    private Double totalPrice;

    public Long getRenterId() {
        return renterId;
    }

    public void setRenterId(Long renterId) {
        this.renterId = renterId;
    }

    public String getRenterEmail() {
        return renterEmail;
    }

    public void setRenterEmail(String renterEmail) {
        this.renterEmail = renterEmail;
    }

    public Long getCarId() {
        return carId;
    }

    public void setCarId(Long carId) {
        this.carId = carId;
    }

    public String getCarPlate() {
        return carPlate;
    }

    public void setCarPlate(String carPlate) {
        this.carPlate = carPlate;
    }

    public Date getStart() {
        return start;
    }

    public void setStart(Date start) {
        this.start = start;
    }

    public Date getEnd() {
        return end;
    }

    public void setEnd(Date end) {
        this.end = end;
    }

    public Double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(Double totalPrice) {
        this.totalPrice = totalPrice;
    }
}
