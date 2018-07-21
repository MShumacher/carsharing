package com.training.carsharing.impl;

import com.training.carsharing.IModelService;
import com.training.carsharing.dao.IModelDao;
import com.training.carsharing.model.IModel;
import com.training.carsharing.model.impl.Model;
import org.springframework.stereotype.Service;

@Service
public class ModelServiceImpl extends AbstractServiceImpl<IModel, IModelDao, Integer> implements IModelService {

    public ModelServiceImpl() { super(Model.class); }

}
