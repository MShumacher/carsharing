package com.training.carsharing.impl;

import com.training.carsharing.ICarsPhotoService;
import com.training.carsharing.dao.ICarsPhotoDao;
import com.training.carsharing.model.impl.CarsPhoto;
import org.springframework.stereotype.Service;

@Service
public class CarsPhotoServiceImpl extends AbstractServiceImpl<CarsPhoto, ICarsPhotoDao, Integer> implements ICarsPhotoService {

    public CarsPhotoServiceImpl() {
        super(CarsPhoto.class);
    }
}
