package com.training.carsharing.impl;

import com.training.carsharing.FuelService;
import com.training.carsharing.model.impl.Fuel;
import com.training.carsharing.repository.FuelRepository;
import org.springframework.stereotype.Service;

@Service
public class CustomFuelService extends CustomAbstractService<Fuel, Long> implements FuelService {

    public CustomFuelService() { super(Fuel.class); }

}
