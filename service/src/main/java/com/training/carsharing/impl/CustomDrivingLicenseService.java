package com.training.carsharing.impl;

import com.training.carsharing.DrivingLicenseService;
import com.training.carsharing.model.impl.DrivingLicense;
import com.training.carsharing.model.impl.UserAccount;
import com.training.carsharing.repository.DrivingLicenseRepository;
import com.training.carsharing.repository.UserAccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Date;

@Service
public class CustomDrivingLicenseService extends CustomAbstractService<DrivingLicense, Long> implements DrivingLicenseService {

    public CustomDrivingLicenseService() {
        super(DrivingLicense.class);
    }
}
