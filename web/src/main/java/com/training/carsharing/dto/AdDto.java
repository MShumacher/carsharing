package com.training.carsharing.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Digits;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@Setter
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
}
