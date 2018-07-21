package com.training.carsharing.model.impl;

import com.training.carsharing.model.ICar;
import com.training.carsharing.model.IModel;
import com.training.carsharing.model.IParameter;
import com.training.carsharing.model.IUserAccount;
import com.training.carsharing.model.enums.*;
import jdk.nashorn.internal.objects.annotations.Getter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
public class Car extends BaseEntity implements ICar {

    @JoinTable(name = "car_2_parameter", joinColumns = { @JoinColumn(name = "car_id") }, inverseJoinColumns = {
            @JoinColumn(name = "parameter_id") })
    @ManyToMany(targetEntity = Parameter.class, fetch = FetchType.LAZY)
//    @OrderBy("title ASC")
    private Set<IParameter> parameters = new HashSet<>();

    @ManyToOne(fetch = FetchType.LAZY, targetEntity = UserAccount.class)
    private IUserAccount userAccount;

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
    public Set<IParameter> getParameters() {
        return parameters;
    }

    @Override
    public void setParameters(Set<IParameter> parameters) {
        this.parameters = parameters;
    }

    @Override
    public IUserAccount getUserAccount() {
        return userAccount;
    }

    @Override
    public void setUserAccount(IUserAccount userAccount) {
        this.userAccount = userAccount;
    }

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
                ", userAccount=" + userAccount.getName() +
                ", model=" + model.getName() +
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Car car = (Car) o;
        return Objects.equals(userAccount, car.userAccount) &&
                Objects.equals(model, car.model) &&
                Objects.equals(year, car.year) &&
                Objects.equals(plate, car.plate) &&
                Objects.equals(mileage, car.mileage) &&
                Objects.equals(seats, car.seats) &&
                gearbox == car.gearbox &&
                bodyType == car.bodyType &&
                drive == car.drive &&
                engineType == car.engineType &&
                fuel == car.fuel &&
                Objects.equals(charge, car.charge) &&
                Objects.equals(conditions, car.conditions) &&
                Objects.equals(insurance, car.insurance) &&
                Objects.equals(parameters, car.parameters);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userAccount, model, year, plate, mileage, seats, gearbox, bodyType, drive, engineType, fuel, charge, conditions, insurance, parameters);
    }
}
