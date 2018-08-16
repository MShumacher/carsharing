package com.training.carsharing.impl;

import com.training.carsharing.CarsPhotoService;
import com.training.carsharing.model.impl.CarsPhoto;
import com.training.carsharing.repository.CarsPhotoRepository;
import org.springframework.stereotype.Service;

@Service
public class CustomCarsPhotoService extends CustomAbstractService<CarsPhoto, CarsPhotoRepository, Integer> implements CarsPhotoService {

    public CustomCarsPhotoService() {
        super(CarsPhoto.class);
    }
}
