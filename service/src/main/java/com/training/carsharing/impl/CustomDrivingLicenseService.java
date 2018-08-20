package com.training.carsharing.impl;

import com.training.carsharing.DrivingLicenseService;
import com.training.carsharing.model.impl.DrivingLicense;
import com.training.carsharing.model.impl.UserAccount;
import com.training.carsharing.repository.DrivingLicenseRepository;
import com.training.carsharing.repository.UserAccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class CustomDrivingLicenseService extends CustomAbstractService<DrivingLicense, Long> implements DrivingLicenseService {

    @Autowired
    private DrivingLicenseRepository drivingLicenseRepository;
    @Autowired
    private UserAccountRepository userAccountRepository;

    public CustomDrivingLicenseService() {
        super(DrivingLicense.class);
    }

    @Override
    public DrivingLicense save(final DrivingLicense drivingLicense) {
        UserAccount userAccount = drivingLicense.getUserAccount();
        final Date modifiedOn = new Date();
        drivingLicense.setUpdated(modifiedOn);
        if (userAccount.getId() == null) {
            userAccount.setUpdated(modifiedOn);
            userAccount.setCreated(modifiedOn);
            LOGGER.info("new saved entity: {}", userAccount);
            drivingLicense.setUserAccount(userAccountRepository.save(userAccount));
        }
        if (drivingLicense.getId() == null) {
            drivingLicense.setCreated(modifiedOn);
            LOGGER.info("new saved entity: {}", drivingLicense);
        } else {
            LOGGER.info("updated entity: {}", drivingLicense);
        }
        return (DrivingLicense) drivingLicenseRepository.save(drivingLicense);
    }
}
