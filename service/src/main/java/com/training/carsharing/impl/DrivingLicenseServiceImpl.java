package com.training.carsharing.impl;

import com.training.carsharing.IDrivingLicenseService;
import com.training.carsharing.dao.IDrivingLicenseDao;
import com.training.carsharing.model.impl.DrivingLicense;
import org.springframework.stereotype.Service;

@Service
public class DrivingLicenseServiceImpl extends AbstractServiceImpl<DrivingLicense, IDrivingLicenseDao, Integer> implements IDrivingLicenseService {

    public DrivingLicenseServiceImpl() { super(DrivingLicense.class); }

}
