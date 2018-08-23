package com.training.carsharing;

import com.training.carsharing.model.impl.Brand;

import java.util.List;

public interface BrandService extends AbstractService<Brand, Long> {

    List<Brand> findByName(String name);
}

