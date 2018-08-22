package com.training.carsharing.impl;

import com.training.carsharing.PassportService;
import com.training.carsharing.model.impl.Passport;
import org.springframework.stereotype.Service;

@Service
public class CustomPassportService extends CustomAbstractService<Passport, Long> implements PassportService {

    public CustomPassportService() {
        super(Passport.class);
    }
}
