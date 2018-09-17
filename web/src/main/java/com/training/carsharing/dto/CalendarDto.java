package com.training.carsharing.dto;

import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.*;
import java.util.Date;

@Getter
@Setter
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
}
