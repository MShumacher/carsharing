package com.training.carsharing.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Size;

@Getter
@Setter
public class BaseWithNameDto extends BaseDto {

    @Size(min = 1, max = 50)
    private String name;
}
