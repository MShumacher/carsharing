package com.training.carsharing;

import com.training.carsharing.model.impl.Passport;
import com.training.carsharing.repository.PassportRepository;

import java.util.List;

public interface PassportService extends AbstractService<Passport, Long> {

    List<Passport> findByUserAccountIdIsNull();

    Passport findByUserAccountId(Long id);
}

