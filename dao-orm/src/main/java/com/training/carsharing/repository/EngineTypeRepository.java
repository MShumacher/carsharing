package com.training.carsharing.repository;

import com.training.carsharing.model.impl.EngineType;
import com.training.carsharing.repository.customrepository.EngineTypeRepositoryCustom;

public interface EngineTypeRepository extends AbstractRepository<EngineType, Long>, EngineTypeRepositoryCustom {

}
