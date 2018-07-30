package com.training.carsharing.impl;

import com.training.carsharing.ICarService;
import com.training.carsharing.dao.ICarDao;
import com.training.carsharing.model.impl.Car;
import org.springframework.stereotype.Service;

@Service
public class CarServiceImpl extends AbstractServiceImpl<Car, ICarDao, Integer> implements ICarService {

    public CarServiceImpl() {
        super(Car.class);
    }
}
