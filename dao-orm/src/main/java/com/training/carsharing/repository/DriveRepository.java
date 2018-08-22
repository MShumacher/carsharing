package com.training.carsharing.repository;

import com.training.carsharing.model.impl.Drive;
import com.training.carsharing.repository.customrepository.DriveRepositoryCustom;

public interface DriveRepository extends AbstractRepository<Drive, Long>, DriveRepositoryCustom {

}
