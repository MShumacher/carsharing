package com.training.carsharing.repository;

import com.training.carsharing.model.impl.DrivingLicense;
import com.training.carsharing.repository.customrepository.DrivingLicenseRepositoryCustom;

import java.util.List;

public interface DrivingLicenseRepository extends AbstractRepository<DrivingLicense, Long>, DrivingLicenseRepositoryCustom {

    List<DrivingLicense> findByUserAccountIdIsNull();

    DrivingLicense findByUserAccountId(Long id);
}
