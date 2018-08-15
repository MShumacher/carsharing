package com.training.carsharing.impl;

import com.training.carsharing.ModelService;
import com.training.carsharing.model.impl.Model;
import com.training.carsharing.repository.ModelRepository;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

@Service
public class CustomModelService extends CustomAbstractService<Model, ModelRepository, Integer> implements ModelService {

    public CustomModelService() { super(Model.class); }

}
