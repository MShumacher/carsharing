package com.training.carsharing.model.impl;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
public class Car extends BaseEntity {

    @OneToOne(fetch = FetchType.LAZY, mappedBy = "car", targetEntity = Ad.class)
    private Ad ad;

    @JoinTable(name = "car_2_car_parameter", joinColumns = {@JoinColumn(name = "car_id")}, inverseJoinColumns = {
            @JoinColumn(name = "car_parameter_id")})
    @ManyToMany(targetEntity = CarParameter.class, fetch = FetchType.LAZY)
    //@ManyToMany(targetEntity = CarParameter.class, cascade = CascadeType.MERGE, fetch = FetchType.LAZY)
//    @OrderBy("title ASC")
    private Set<CarParameter> carParameter = new HashSet<>();

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
