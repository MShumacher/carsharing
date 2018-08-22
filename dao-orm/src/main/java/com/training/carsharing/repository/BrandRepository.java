package com.training.carsharing.repository;

import com.training.carsharing.model.impl.Brand;
import com.training.carsharing.repository.customrepository.BrandRepositoryCustom;

//@Repository
public interface BrandRepository extends AbstractRepository<Brand, Long>, BrandRepositoryCustom {

}
