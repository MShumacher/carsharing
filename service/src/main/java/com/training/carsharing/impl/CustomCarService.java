package com.training.carsharing.impl;

import com.training.carsharing.CarService;
import com.training.carsharing.model.impl.Car;
import com.training.carsharing.repository.CarRepository;
import org.springframework.stereotype.Service;

@Service
public class CustomCarService extends CustomAbstractService<Car, Long> implements CarService {

    public CustomCarService() {
        super(Car.class);
    }
}
