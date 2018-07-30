package com.training.carsharing.impl;

import com.training.carsharing.IBrandService;
import com.training.carsharing.dao.IBrandDao;
import com.training.carsharing.model.impl.Brand;
import org.springframework.stereotype.Service;

@Service
public class BrandServiceImpl extends AbstractServiceImpl<Brand, IBrandDao, Integer> implements IBrandService {

    public BrandServiceImpl() { super(Brand.class); }

}
