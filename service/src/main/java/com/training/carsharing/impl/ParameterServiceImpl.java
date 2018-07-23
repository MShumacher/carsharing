package com.training.carsharing.impl;

import com.training.carsharing.IParameterService;
import com.training.carsharing.dao.IParameterDao;
import com.training.carsharing.model.IParameter;
import com.training.carsharing.model.impl.Parameter;
import org.springframework.stereotype.Service;

@Service
public class ParameterServiceImpl extends AbstractServiceImpl<IParameter, IParameterDao, Integer> implements IParameterService {

    public ParameterServiceImpl() { super(Parameter.class); }

}
