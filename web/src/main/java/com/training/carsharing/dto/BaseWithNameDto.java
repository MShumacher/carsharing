package com.training.carsharing.dto;

import javax.validation.constraints.Size;

public class BaseWithNameDto extends BaseDto {

    @Size(min = 1, max = 50)
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
