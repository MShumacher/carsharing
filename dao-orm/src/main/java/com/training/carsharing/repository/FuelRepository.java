package com.training.carsharing.repository;

import com.training.carsharing.model.impl.Fuel;
import com.training.carsharing.repository.customrepository.FuelRepositoryCustom;

public interface FuelRepository extends AbstractRepository<Fuel, Long>, FuelRepositoryCustom {

}
