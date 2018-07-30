package com.training.carsharing.impl;

import com.training.carsharing.IBodyTypeService;
import com.training.carsharing.dao.IBodyTypeDao;
import com.training.carsharing.model.impl.BodyType;
import org.springframework.stereotype.Service;

@Service
public class BodyTypeServiceImpl extends AbstractServiceImpl<BodyType, IBodyTypeDao, Integer> implements IBodyTypeService {

    public BodyTypeServiceImpl() { super(BodyType.class); }

}
