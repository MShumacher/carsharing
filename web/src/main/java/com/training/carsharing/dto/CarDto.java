package com.training.carsharing.dto;

import javax.validation.constraints.*;
import java.util.Set;

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

    public Long getModelId() {
        return modelId;
    }

    public void setModelId(Long modelId) {
        this.modelId = modelId;
    }

    public String getModelName() {
        return modelName;
    }

    public void setModelName(String modelName) {
        this.modelName = modelName;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public String getPlate() {
        return plate;
    }

    public void setPlate(String plate) {
        this.plate = plate;
    }

    public int getMileage() {
        return mileage;
    }

    public void setMileage(int mileage) {
        this.mileage = mileage;
    }

    public int getSeats() {
        return seats;
    }

    public void setSeats(int seats) {
        this.seats = seats;
    }

    public Long getGearboxId() {
        return gearboxId;
    }

    public void setGearboxId(Long gearboxId) {
        this.gearboxId = gearboxId;
    }

    public String getGearboxName() {
        return gearboxName;
    }

    public void setGearboxName(String gearboxName) {
        this.gearboxName = gearboxName;
    }

    public Long getBodyTypeId() {
        return bodyTypeId;
    }

    public void setBodyTypeId(Long bodyTypeId) {
        this.bodyTypeId = bodyTypeId;
    }

    public String getBodyTypeName() {
        return bodyTypeName;
    }

    public void setBodyTypeName(String bodyTypeName) {
        this.bodyTypeName = bodyTypeName;
    }

    public Long getDriveId() {
        return driveId;
    }

    public void setDriveId(Long driveId) {
        this.driveId = driveId;
    }

    public String getDriveName() {
        return driveName;
    }

    public void setDriveName(String driveName) {
        this.driveName = driveName;
    }

    public Long getEngineTypeId() {
        return engineTypeId;
    }

    public void setEngineTypeId(Long engineTypeId) {
        this.engineTypeId = engineTypeId;
    }

    public String getEngineTypeName() {
        return engineTypeName;
    }

    public void setEngineTypeName(String engineTypeName) {
        this.engineTypeName = engineTypeName;
    }

    public Double getCharge() {
        return charge;
    }

    public void setCharge(Double charge) {
        this.charge = charge;
    }

    public String getConditions() {
        return conditions;
    }

    public void setConditions(String conditions) {
        this.conditions = conditions;
    }

    public String getInsurance() {
        return insurance;
    }

    public void setInsurance(String insurance) {
        this.insurance = insurance;
    }

    public Set<Long> getCarParameterIds() {
        return carParameterIds;
    }

    public void setCarParameterIds(Set<Long> carParameterIds) {
        this.carParameterIds = carParameterIds;
    }
}
