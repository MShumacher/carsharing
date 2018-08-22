package com.training.carsharing.repository;

import com.training.carsharing.model.impl.Gearbox;
import com.training.carsharing.repository.customrepository.GearboxRepositoryCustom;

public interface GearboxRepository extends AbstractRepository<Gearbox, Long>, GearboxRepositoryCustom {

}
