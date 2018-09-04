package com.training.carsharing.controller.db;

import com.training.carsharing.dto.CarDto;
import com.training.carsharing.model.impl.Car;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping(value = "/car")
public class CarController extends AbstractController<Car, CarDto, Long> {

    protected CarController() {
        super(CarDto.class);
    }

    @Override
    public Map<String, Object> getHashMapWithAllCommonForms(Long id) {
        final Map<String, Object> hashMap = new HashMap<>();
        loadCommonFormBrands(hashMap);
        loadCommonFormModels(hashMap);
        //TODO load models for current brand
        //loadCommonFormModelsForBrand(hashMap);
        loadCommonFormGearboxes(hashMap);
        loadCommonFormBodyTypes(hashMap);
        loadCommonFormDrives(hashMap);
        loadCommonFormEngineTypes(hashMap);
        loadCommonFormCarParameters(hashMap);
        return hashMap;
    }
}

