package com.training.carsharing.dto;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class ModelDto extends BaseDto {

    @Size(min = 1, max = 50)
    private String name;

    @NotNull
    private Long brandId;
    private String brandName;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getBrandId() {
        return brandId;
    }

    public void setBrandId(Long brandId) {
        this.brandId = brandId;
    }

    public String getBrandName() {
        return brandName;
    }

    public void setBrandName(String brandName) {
        this.brandName = brandName;
    }
}
