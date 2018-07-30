package com.training.carsharing.impl;

import com.training.carsharing.IEngineTypeService;
import com.training.carsharing.dao.IEngineTypeDao;
import com.training.carsharing.model.impl.EngineType;
import org.springframework.stereotype.Service;

@Service
public class EngineTypeServiceImpl extends AbstractServiceImpl<EngineType, IEngineTypeDao, Integer> implements IEngineTypeService {

    public EngineTypeServiceImpl() { super(EngineType.class); }

}
