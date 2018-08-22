package com.training.carsharing.repository;

import com.training.carsharing.model.impl.BodyType;
import com.training.carsharing.repository.customrepository.BodyTypeRepositoryCustom;

public interface BodyTypeRepository extends AbstractRepository<BodyType, Long>, BodyTypeRepositoryCustom {

}
