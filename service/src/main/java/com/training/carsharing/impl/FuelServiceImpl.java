package com.training.carsharing.impl;

import com.training.carsharing.IFuelService;
import com.training.carsharing.dao.IFuelDao;
import com.training.carsharing.model.IFuel;
import com.training.carsharing.model.impl.Fuel;
import org.springframework.stereotype.Service;

@Service
public class FuelServiceImpl extends AbstractServiceImpl<IFuel, IFuelDao, Integer> implements IFuelService {

    public FuelServiceImpl() { super(Fuel.class); }

}
