package com.training.carsharing.dto;

import javax.validation.constraints.Size;

public class BrandDto extends BaseDto {

    private String name;

    private Integer version;

    @Size(min = 1, max = 50)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }
}
