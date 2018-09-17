package com.training.carsharing.model.impl;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity
@Getter
@Setter
public class CarParameter extends BaseEntity {

    @Column
    private String name;

    @Override
    public String toString() {
        return "CarParameter{" + super.toString() +
                ", name='" + name + '\'' +
                "}";
    }
}
