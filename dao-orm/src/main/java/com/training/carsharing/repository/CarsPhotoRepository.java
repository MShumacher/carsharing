package com.training.carsharing.repository;

import com.training.carsharing.model.impl.CarsPhoto;
import com.training.carsharing.repository.customrepository.CarsPhotoRepositoryCustom;

public interface CarsPhotoRepository extends AbstractRepository<CarsPhoto, Long>, CarsPhotoRepositoryCustom {
}
