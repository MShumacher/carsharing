package com.training.carsharing;

import com.training.carsharing.model.impl.DrivingLicense;
import com.training.carsharing.model.impl.Passport;
import com.training.carsharing.repository.DrivingLicenseRepository;

import java.util.List;

public interface DrivingLicenseService extends AbstractService<DrivingLicense, Long> {

    List<DrivingLicense> findByUserAccountIsNull();

    DrivingLicense findByUserAccount(Long id);
}

