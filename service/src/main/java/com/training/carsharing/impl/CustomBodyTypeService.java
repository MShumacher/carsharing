package com.training.carsharing.impl;

import com.training.carsharing.BodyTypeService;
import com.training.carsharing.model.impl.BodyType;
import com.training.carsharing.repository.BodyTypeRepository;
import org.springframework.stereotype.Service;

@Service
public class CustomBodyTypeService extends CustomAbstractService<BodyType, BodyTypeRepository, Integer> implements BodyTypeService {

    public CustomBodyTypeService() { super(BodyType.class); }

}
