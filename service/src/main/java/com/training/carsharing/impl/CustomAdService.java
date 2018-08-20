package com.training.carsharing.impl;

import com.training.carsharing.AdService;
import com.training.carsharing.model.impl.Ad;
import com.training.carsharing.model.impl.Car;
import com.training.carsharing.repository.AdRepository;
import com.training.carsharing.repository.CarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class CustomAdService extends CustomAbstractService<Ad, Long> implements AdService {

    @Autowired
    private AdRepository adRepository;
    @Autowired
    private CarRepository carRepository;

    public CustomAdService() {
        super(Ad.class);
    }

    @Override
    public Ad save(final Ad ad) {
        Car car = ad.getCar();
        final Date modifiedOn = new Date();
        ad.setUpdated(modifiedOn);
        if (car.getId() == null) {
            car.setUpdated(modifiedOn);
            car.setCreated(modifiedOn);
            LOGGER.info("new saved entity: {}", car);
            ad.setCar(carRepository.save(car));
        }
        if (ad.getId() == null) {
            ad.setCreated(modifiedOn);
            LOGGER.info("new saved entity: {}", ad);
        } else {
            LOGGER.info("updated entity: {}", ad);
        }
        return (Ad) adRepository.save(ad);
    }
}
