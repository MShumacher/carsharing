package com.training.carsharing.repository;

import com.training.carsharing.model.impl.Car;
import com.training.carsharing.repository.customrepository.CarRepositoryCustom;

public interface CarRepository extends AbstractRepository<Car, Long>, CarRepositoryCustom {
}
