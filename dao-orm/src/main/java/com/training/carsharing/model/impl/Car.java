package com.training.carsharing.model.impl;

import com.training.carsharing.model.IAd;
import com.training.carsharing.model.ICar;
import com.training.carsharing.model.IModel;
import com.training.carsharing.model.IParameter;
import com.training.carsharing.model.enums.*;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Car extends BaseEntity implements ICar {

    @OneToOne(fetch = FetchType.LAZY, mappedBy = "car", targetEntity = Ad.class)
    private IAd ad;

    @JoinTable(name = "car_2_parameter", joinColumns = { @JoinColumn(name = "car_id") }, inverseJoinColumns = {
            @JoinColumn(name = "parameter_id") })
    @ManyToMany(targetEntity = CarParameter.class, fetch = FetchType.LAZY)
//    @OrderBy("title ASC")
    private Set<IParameter> parameters = new HashSet<>();

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

    @Column
    @Enumerated(EnumType.STRING)
    private Gearbox gearbox;

    @Column
    @Enumerated(EnumType.STRING)
    private BodyType bodyType;

    @Column
    @Enumerated(EnumType.STRING)
    private Drive drive;

    @Column
    @Enumerated(EnumType.STRING)
    private EngineType engineType;

    @Column
    @Enumerated(EnumType.STRING)
    private Fuel fuel;

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
    public Set<IParameter> getParameters() {
        return parameters;
    }

    @Override
    public void setParameters(Set<IParameter> parameters) {
        this.parameters = parameters;
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
    public Gearbox getGearbox() {
        return gearbox;
    }

    @Override
    public void setGearbox(Gearbox gearbox) {
        this.gearbox = gearbox;
    }

    @Override
    public BodyType getBodyType() {
        return bodyType;
    }

    @Override
    public void setBodyType(BodyType bodyType) {
        this.bodyType = bodyType;
    }

    @Override
    public Drive getDrive() {
        return drive;
    }

    @Override
    public void setDrive(Drive drive) {
        this.drive = drive;
    }

    @Override
    public EngineType getEngineType() {
        return engineType;
    }

    @Override
    public void setEngineType(EngineType engineType) {
        this.engineType = engineType;
    }

    @Override
    public Fuel getFuel() {
        return fuel;
    }

    @Override
    public void setFuel(Fuel fuel) {
        this.fuel = fuel;
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
                ", gearbox='" + gearbox + '\'' +
                ", bodyType='" + bodyType + '\'' +
                ", drive='" + drive + '\'' +
                ", engineType='" + engineType + '\'' +
                ", fuel='" + fuel + '\'' +
                ", charge=" + charge +
                ", conditions='" + conditions + '\'' +
                ", insurance='" + insurance + '\'' +
                "}";
    }
}
