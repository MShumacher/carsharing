package com.training.carsharing.impl;

import com.training.carsharing.EngineTypeService;
import com.training.carsharing.model.impl.EngineType;
import com.training.carsharing.repository.EngineTypeRepository;
import org.springframework.stereotype.Service;

@Service
public class CustomEngineTypeService extends CustomAbstractService<EngineType, Integer> implements EngineTypeService {

    public CustomEngineTypeService() { super(EngineType.class); }

}
