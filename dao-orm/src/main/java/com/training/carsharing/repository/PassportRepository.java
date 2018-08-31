package com.training.carsharing.repository;

import com.training.carsharing.model.impl.Passport;
import com.training.carsharing.repository.customrepository.PassportRepositoryCustom;

import java.util.List;

public interface PassportRepository extends AbstractRepository<Passport, Long>, PassportRepositoryCustom {

    List<Passport> findByUserAccountIdIsNull();

    Passport findByUserAccountId(Long id);
}
