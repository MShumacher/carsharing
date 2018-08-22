package com.training.carsharing.repository;

import com.training.carsharing.model.impl.DrivingLicense;
import com.training.carsharing.repository.customrepository.DrivingLicenseRepositoryCustom;

public interface DrivingLicenseRepository extends AbstractRepository<DrivingLicense, Long>, DrivingLicenseRepositoryCustom {

}
