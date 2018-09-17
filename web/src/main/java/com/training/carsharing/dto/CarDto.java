package com.training.carsharing.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.*;
import java.util.Set;

@Getter
@Setter
public class CarDto extends BaseDto {

    @NotNull
    private Long brandId;

    private String brandName;

    @NotNull
    private Long modelId;

    private String modelName;

    @Min(1900)
    @Max(2018)
    @NotNull
    private int year;

    @Size(min = 1, max = 50)
    private String plate;

    @Min(0)
    @NotNull
    private int mileage;

    @Min(1)
    @NotNull
    private int seats;

    @NotNull
    private Long gearboxId;

    private String gearboxName;

    @NotNull
    private Long bodyTypeId;

    private String bodyTypeName;

    @NotNull
    private Long driveId;

    private String driveName;

    @NotNull
    private Long engineTypeId;

    private String engineTypeName;

    @Min(0)
    @Digits(integer = 2, fraction = 2) // валидация на 2 знака после запятой
    @NotNull
    private Double charge;

    @Size(min = 1, max = 1000)
    private String conditions;

    @Size(min = 1, max = 500)
    private String insurance;

    private Set<Long> carParameterIds;
}
