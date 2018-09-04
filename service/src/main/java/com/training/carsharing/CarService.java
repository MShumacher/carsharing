package com.training.carsharing;

import com.training.carsharing.model.impl.Car;

import java.util.List;

public interface CarService extends AbstractService<Car, Long> {

    List<Car> findWithoutAd();

    Car findByAd(Long id);
}
