package com.training.carsharing.repository;

import com.training.carsharing.model.impl.Passport;
import com.training.carsharing.repository.customrepository.PassportRepositoryCustom;

public interface PassportRepository extends AbstractRepository<Passport, Long>, PassportRepositoryCustom {

}
