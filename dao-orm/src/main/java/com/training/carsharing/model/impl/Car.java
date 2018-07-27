package com.training.carsharing.model.impl;

import com.training.carsharing.model.*;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Car extends BaseEntity implements ICar {

    @OneToOne(fetch = FetchType.LAZY, mappedBy = "car", targetEntity = Ad.class)
    private IAd ad;

    @JoinTable(name = "car_2_car_parameter", joinColumns = { @JoinColumn(name = "car_id") }, inverseJoinColumns = {
            @JoinColumn(name = "car_parameter_id") })
    @ManyToMany(targetEntity = CarParameter.class, fetch = FetchType.LAZY)
//    @OrderBy("title ASC")
    private Set<ICarParameter> carParameter = new HashSet<>();

//    @ManyToOne(fetch = FetchType.LAZY, targetEntity = UserAccount.class)
//    private IUserAccount userAccount;

    @ManyToOne(fetch = FetchType.LAZY, targetEntity = Model.class)
    private IModel model;

    @Column
    private Integer year;

    @Column
    private String plate;

    @Column
    private Integer mileage;

    @Column
    private Integer seats;

    @ManyToOne(fetch = FetchType.LAZY, targetEntity = Gearbox.class)
    private IGearbox gearbox;

    @ManyToOne(fetch = FetchType.LAZY, targetEntity = BodyType.class)
    private IBodyType bodyType;

    @ManyToOne(fetch = FetchType.LAZY, targetEntity = Drive.class)
    private IDrive drive;

    @ManyToOne(fetch = FetchType.LAZY, targetEntity = EngineType.class)
    private IEngineType engineType;

    @Column
    private Double charge;

    @Column
    private String conditions;

    @Column
    private String insurance;

    @Override
    public IAd getAd() {
        return ad;
    }

    @Override
    public void setAd(final IAd ad) {
        this.ad = ad;
    }

    @Override
    public Set<ICarParameter> getCarParameter() {
        return carParameter;
    }

    @Override
    public void setCarParameter(Set<ICarParameter> carParameter) {
        this.carParameter = carParameter;
    }

//    @Override
//    public IUserAccount getUserAccount() {
//        return userAccount;
//    }

//    @Override
//    public void setUserAccount(IUserAccount userAccount) {
//        this.userAccount = userAccount;
//    }

    @Override
    public IModel getModel() {
        return model;
    }

    @Override
    public void setModel(IModel model) {
        this.model = model;
    }

    @Override
    public Integer getYear() {
        return year;
    }

    @Override
    public void setYear(Integer year) {
        this.year = year;
    }

    @Override
    public String getPlate() {
        return plate;
    }

    @Override
    public void setPlate(String plate) {
        this.plate = plate;
    }

    @Override
    public Integer getMileage() {
        return mileage;
    }

    @Override
    public void setMileage(Integer mileage) {
        this.mileage = mileage;
    }

    @Override
    public Integer getSeats() {
        return seats;
    }

    @Override
    public void setSeats(Integer seats) {
        this.seats = seats;
    }

    @Override
    public IGearbox getGearbox() {
        return gearbox;
    }

    @Override
    public void setGearbox(IGearbox gearbox) {
        this.gearbox = gearbox;
    }

    @Override
    public IBodyType getBodyType() {
        return bodyType;
    }

    @Override
    public void setBodyType(IBodyType bodyType) {
        this.bodyType = bodyType;
    }

    @Override
    public IDrive getDrive() {
        return drive;
    }

    @Override
    public void setDrive(IDrive drive) {
        this.drive = drive;
    }

    @Override
    public IEngineType getEngineType() {
        return engineType;
    }

    @Override
    public void setEngineType(IEngineType engineType) {
        this.engineType = engineType;
    }

    @Override
    public Double getCharge() {
        return charge;
    }

    @Override
    public void setCharge(Double charge) {
        this.charge = charge;
    }

    @Override
    public String getConditions() {
        return conditions;
    }

    @Override
    public void setConditions(String conditions) {
        this.conditions = conditions;
    }

    @Override
    public String getInsurance() {
        return insurance;
    }

    @Override
    public void setInsurance(String insurance) {
        this.insurance = insurance;
    }

     @Override
    public String toString() {
        return "Car{" + super.toString() +
//                ", userAccount=" + userAccount.getName() +
                ", modelId=" + model.getId() +
                ", year=" + year +
                ", plate='" + plate + '\'' +
                ", mileage=" + mileage +
                ", seats=" + seats +
                ", gearboxId='" + gearbox.getId() + '\'' +
                ", bodyTypeId='" + bodyType.getId() + '\'' +
                ", driveId='" + drive.getId() + '\'' +
                ", engineTypeId='" + engineType.getId() + '\'' +
                ", charge=" + charge +
                ", conditions='" + conditions + '\'' +
                ", insurance='" + insurance + '\'' +
                "}";
    }
}
