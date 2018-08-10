package com.training.carsharing.impl;

import com.training.carsharing.GearboxService;
import com.training.carsharing.model.impl.Gearbox;
import com.training.carsharing.repository.GearboxRepository;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

@Service
public class CustomGearboxService extends CustomAbstractService<Gearbox, GearboxRepository, Integer> implements GearboxService {

    public CustomGearboxService() { super(Gearbox.class); }

}
