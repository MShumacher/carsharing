package com.training.carsharing.dto;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class EngineTypeDto extends BaseWithNameDto {

    @NotNull
    private Long fuelId;

    private String fuelName;

    public Long getFuelId() {
        return fuelId;
    }

    public void setFuelId(Long fuelId) {
        this.fuelId = fuelId;
    }

    public String getFuelName() {
        return fuelName;
    }

    public void setFuelName(String fuelName) {
        this.fuelName = fuelName;
    }
}
