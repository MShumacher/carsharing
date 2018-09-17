package com.training.carsharing.model.impl;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity
@Getter
@Setter
public class Drive extends BaseEntity {

    @Column
    private String name;

    @Override
    public String toString() {
        return "drive{" + super.toString() +
                ", name='" + name + '\'' +
                "}";
    }
}
