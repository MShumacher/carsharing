package com.training.carsharing.model.impl;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;

@Entity
@Getter
@Setter
public class Model extends BaseEntity {

    @Column
    private String name;

    @ManyToOne(fetch = FetchType.LAZY, targetEntity = Brand.class)
    private Brand brand;

    @Override
    public String toString() {
        return "model{" + super.toString() + ", name='" + name + '\'' + ", brandId=" + brand.getId() + "}";
    }
}
