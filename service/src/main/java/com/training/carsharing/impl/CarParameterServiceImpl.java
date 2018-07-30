package com.training.carsharing.impl;

import com.training.carsharing.ICarParameterService;
import com.training.carsharing.dao.ICarParameterDao;
import com.training.carsharing.model.impl.CarParameter;
import org.springframework.stereotype.Service;

@Service
public class CarParameterServiceImpl extends AbstractServiceImpl<CarParameter, ICarParameterDao, Integer> implements ICarParameterService {

    public CarParameterServiceImpl() { super(CarParameter.class); }

}
