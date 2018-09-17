package com.training.carsharing.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@Setter
public class ModelDto extends BaseWithNameDto {

    @NotNull
    private Long brandId;

    private String brandName;
}
