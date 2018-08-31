package com.training.carsharing.dto;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class ModelDto extends BaseWithNameDto {

    @NotNull
    private Long brandId;

    private String brandName;

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
