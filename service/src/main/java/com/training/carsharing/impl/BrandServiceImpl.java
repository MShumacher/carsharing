package com.training.carsharing.impl;

import com.training.carsharing.IBrandService;
import com.training.carsharing.dao.IBrandDao;
import com.training.carsharing.model.IBrand;
import com.training.carsharing.model.impl.Brand;
import org.springframework.stereotype.Service;

@Service
public class BrandServiceImpl extends AbstractServiceImpl<IBrand, IBrandDao, Integer> implements IBrandService {

    public BrandServiceImpl() { super(Brand.class); }

}