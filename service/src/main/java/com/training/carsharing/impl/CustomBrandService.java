package com.training.carsharing.impl;

import com.training.carsharing.BrandService;
import com.training.carsharing.model.impl.Brand;
import com.training.carsharing.repository.BrandRepository;
import org.springframework.stereotype.Service;

@Service
public class CustomBrandService extends CustomAbstractService<Brand, BrandRepository, Integer> implements BrandService {

    public CustomBrandService() { super(Brand.class);
    }
}
