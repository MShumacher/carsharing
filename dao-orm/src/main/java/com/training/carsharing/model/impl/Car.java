package com.training.carsharing.model.impl;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Car extends BaseEntity {

    @OneToOne(fetch = FetchType.LAZY, mappedBy = "car", targetEntity = Ad.class)
    private Ad ad;

    @JoinTable(name = "car_2_car_parameter", joinColumns = {@JoinColumn(name = "car_id")}, inverseJoinColumns = {
            @JoinColumn(name = "car_parameter_id")})
    @ManyToMany(targetEntity = CarParameter.class, fetch = FetchType.LAZY)
    //@ManyToMany(targetEntity = CarParameter.class, cascade = CascadeType.MERGE, fetch = FetchType.LAZY)
//    @OrderBy("title ASC")
    private Set<CarParameter> carParameter = new HashSet<>();

//    @ManyToOne(fetch = FetchType.LAZY, targetEntity = UserAccount.class)
//    private IUserAccount userAccount;

    @ManyToOne(fetch = FetchType.LAZY, targetEntity = Model.class)
    private Model model;

    @Column
    private Integer year;

    @Column
    private String plate;

    @Column
    private Integer mileage;

    @Column
    private Integer seats;

    @ManyToOne(fetch = FetchType.LAZY, targetEntity = Gearbox.class)
    private Gearbox gearbox;

    @ManyToOne(fetch = FetchType.LAZY, targetEntity = BodyType.class)
    private BodyType bodyType;

    @ManyToOne(fetch = FetchType.LAZY, targetEntity = Drive.class)
    private Drive drive;

    @ManyToOne(fetch = FetchType.LAZY, targetEntity = EngineType.class)
    private EngineType engineType;

    @Column
    private Double charge;

    @Column
    private String conditions;

    @Column
    private String insurance;

    public Ad getAd() {
        return ad;
    }

    public void setAd(final Ad ad) {
        this.ad = ad;
    }

    public Set<CarParameter> getCarParameter() {
        return carParameter;
    }

    public void setCarParameter(Set<CarParameter> carParameter) {
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

    public Model getModel() {
        return model;
    }

    public void setModel(Model model) {
        this.model = model;
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public String getPlate() {
        return plate;
    }

    public void setPlate(String plate) {
        this.plate = plate;
    }

    public Integer getMileage() {
        return mileage;
    }

    public void setMileage(Integer mileage) {
        this.mileage = mileage;
    }

    public Integer getSeats() {
        return seats;
    }

    public void setSeats(Integer seats) {
        this.seats = seats;
    }

    public Gearbox getGearbox() {
        return gearbox;
    }

    public void setGearbox(Gearbox gearbox) {
        this.gearbox = gearbox;
    }

    public BodyType getBodyType() {
        return bodyType;
    }

    public void setBodyType(BodyType bodyType) {
        this.bodyType = bodyType;
    }

    public Drive getDrive() {
        return drive;
    }

    public void setDrive(Drive drive) {
        this.drive = drive;
    }

    public EngineType getEngineType() {
        return engineType;
    }

    public void setEngineType(EngineType engineType) {
        this.engineType = engineType;
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

    @Override
    public String toString() {
        return "Car{" + super.toString() +
//                ", userAccount=" + userAccount.getName() +
                ", modelId=" + model.getId() +
                ", year=" + year +
                ", plate='" + plate + '\'' +
                ", mileage=" + mileage +
                ", seats=" + seats +
                ", gearboxId=" + gearbox.getId() +
                ", bodyTypeId=" + bodyType.getId() +
                ", driveId=" + drive.getId() +
                ", engineTypeId=" + engineType.getId() +
                ", charge=" + charge +
                ", conditions='" + conditions + '\'' +
                ", insurance='" + insurance + '\'' +
                "}";
    }
}
