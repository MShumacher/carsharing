package com.training.carsharing.impl;

import com.training.carsharing.DrivingLicenseService;
import com.training.carsharing.model.impl.DrivingLicense;
import com.training.carsharing.repository.DrivingLicenseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomDrivingLicenseService extends CustomAbstractService<DrivingLicense, Long> implements DrivingLicenseService {


    @Autowired
    private DrivingLicenseRepository drivingLicenseRepository;

    public CustomDrivingLicenseService() {
        super(DrivingLicense.class);
    }

    public List<DrivingLicense> findByUserAccountIsNull(){
        return drivingLicenseRepository.findByUserAccountIdIsNull();
    }

    public DrivingLicense findByUserAccount(Long id){
        return drivingLicenseRepository.findByUserAccountId(id);
    }
}
