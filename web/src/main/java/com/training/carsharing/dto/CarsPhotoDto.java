package com.training.carsharing.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@Setter
public class CarsPhotoDto extends BaseDto {

    @Size(min = 1, max = 300)
    private String link;

    @NotNull
    private Long carId;

    private String carPlate;
}
