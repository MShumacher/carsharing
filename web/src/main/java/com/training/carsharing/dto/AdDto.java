package com.training.carsharing.dto;

import javax.validation.constraints.Digits;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class AdDto extends BaseWithNameDto {


    @NotNull
    private Long carId;

    private String carPlate;

    @NotNull
    private Long userAccountId;

    private String userAccountEmail;

    @Size(min = 1, max = 500)
    private String address;

    @Min(0)
    @Digits(integer = 10, fraction = 2) // валидация на 2 знака после запятой
    @NotNull
    private Double price;

    @Size(min = 1, max = 1000)
    private String body;

    @NotNull
    private boolean active;

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

    public Long getUserAccountId() {
        return userAccountId;
    }

    public void setUserAccountId(Long userAccountId) {
        this.userAccountId = userAccountId;
    }

    public String getUserAccountEmail() {
        return userAccountEmail;
    }

    public void setUserAccountEmail(String userAccountEmail) {
        this.userAccountEmail = userAccountEmail;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }
}
