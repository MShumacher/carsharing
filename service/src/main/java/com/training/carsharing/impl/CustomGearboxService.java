package com.training.carsharing.impl;

import com.training.carsharing.GearboxService;
import com.training.carsharing.model.impl.Gearbox;
import com.training.carsharing.repository.GearboxRepository;
import org.springframework.stereotype.Service;

@Service
public class CustomGearboxService extends CustomAbstractService<Gearbox, Integer> implements GearboxService {

    public CustomGearboxService() { super(Gearbox.class); }

}
