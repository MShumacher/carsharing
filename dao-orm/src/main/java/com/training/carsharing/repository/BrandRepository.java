package com.training.carsharing.repository;

import com.training.carsharing.model.impl.Brand;
import com.training.carsharing.repository.customrepository.BrandRepositoryCustom;

import java.util.List;

//@Repository
public interface BrandRepository extends AbstractRepository<Brand, Long>, BrandRepositoryCustom {

    List<Brand> findByName(String name);

}
