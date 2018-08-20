package com.training.carsharing.impl;

import com.training.carsharing.AdService;
import com.training.carsharing.model.impl.Ad;
import org.springframework.stereotype.Service;

@Service
public class CustomAdService extends CustomAbstractService<Ad, Long> implements AdService {

    public CustomAdService() {
        super(Ad.class);
    }
}
