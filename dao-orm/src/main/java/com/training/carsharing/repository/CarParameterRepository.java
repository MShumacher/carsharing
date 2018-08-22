package com.training.carsharing.repository;

import com.training.carsharing.model.impl.CarParameter;
import com.training.carsharing.repository.customrepository.CarParameterRepositoryCustom;

public interface CarParameterRepository extends AbstractRepository<CarParameter, Long>, CarParameterRepositoryCustom {

}
