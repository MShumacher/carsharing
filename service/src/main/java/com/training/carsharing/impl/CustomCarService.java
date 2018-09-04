package com.training.carsharing.impl;

import com.training.carsharing.CarService;
import com.training.carsharing.model.impl.Car;
import com.training.carsharing.model.impl.Car;
import com.training.carsharing.repository.CarRepository;
import com.training.carsharing.repository.UserAccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomCarService extends CustomAbstractService<Car, Long> implements CarService {

    public CustomCarService() {
        super(Car.class);
    }

    @Autowired
    private CarRepository carRepository;

    @Override
    public List<Car> findWithoutAd() {
        return carRepository.findWithoutAd();
    }

    @Override
    public Car findByAd(Long id) {
        return carRepository.findByAdId(id);
    }
}
