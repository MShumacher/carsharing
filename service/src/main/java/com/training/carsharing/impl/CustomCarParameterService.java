package com.training.carsharing.impl;

import com.training.carsharing.CarParameterService;
import com.training.carsharing.model.impl.CarParameter;
import com.training.carsharing.repository.CarParameterRepository;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

@Service
public class CustomCarParameterService extends CustomAbstractService<CarParameter, CarParameterRepository, Integer> implements CarParameterService {

    public CustomCarParameterService() { super(CarParameter.class); }

}
