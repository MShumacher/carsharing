package com.training.carsharing.impl;

import com.training.carsharing.IFuelService;
import com.training.carsharing.dao.IFuelDao;
import com.training.carsharing.model.impl.Fuel;
import org.springframework.stereotype.Service;

@Service
public class FuelServiceImpl extends AbstractServiceImpl<Fuel, IFuelDao, Integer> implements IFuelService {

    public FuelServiceImpl() { super(Fuel.class); }

}
