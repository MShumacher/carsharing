package com.training.carsharing.impl;

import com.training.carsharing.IGearboxService;
import com.training.carsharing.dao.IGearboxDao;
import com.training.carsharing.model.impl.Gearbox;
import org.springframework.stereotype.Service;

@Service
public class GearboxServiceImpl extends AbstractServiceImpl<Gearbox, IGearboxDao, Integer> implements IGearboxService {

    public GearboxServiceImpl() { super(Gearbox.class); }

}
