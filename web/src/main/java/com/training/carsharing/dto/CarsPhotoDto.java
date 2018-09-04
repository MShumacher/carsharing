package com.training.carsharing.dto;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class CarsPhotoDto extends BaseDto {

    @Size(min = 1, max = 300)
    private String link;

    @NotNull
    private Long carId;

    private String carPlate;

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
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
}
