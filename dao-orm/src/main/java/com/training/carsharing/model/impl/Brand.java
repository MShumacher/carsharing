package com.training.carsharing.model.impl;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table
@Getter
@Setter
public class Brand extends BaseEntity {

    @Column
    private String name;

    @Override
    public String toString() {
        return "brand{" + super.toString() +
                ", name='" + name + '\'' +
                "}";
    }
}
