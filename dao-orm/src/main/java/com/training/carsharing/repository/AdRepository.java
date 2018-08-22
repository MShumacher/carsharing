package com.training.carsharing.repository;

import com.training.carsharing.model.impl.Ad;
import com.training.carsharing.repository.customrepository.AdRepositoryCustom;

public interface AdRepository extends AbstractRepository<Ad, Long>, AdRepositoryCustom {
}
