package com.training.carsharing.repository;

import com.training.carsharing.model.impl.Model;
import com.training.carsharing.repository.customrepository.ModelRepositoryCustom;

public interface ModelRepository extends AbstractRepository<Model, Long>, ModelRepositoryCustom {

}
