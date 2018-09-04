package com.training.carsharing.controller.db;

import com.training.carsharing.dto.CarsPhotoDto;
import com.training.carsharing.model.impl.CarsPhoto;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping(value = "/carsphoto")
public class CarsPhotoController extends AbstractController<CarsPhoto, CarsPhotoDto, Long> {

    protected CarsPhotoController() {
        super(CarsPhotoDto.class);
    }

    @Override
    public Map<String, Object> getHashMapWithAllCommonForms(Long id) {
        final Map<String, Object> hashMap = new HashMap<>();
        loadCommonFormCars(hashMap);
        return hashMap;
    }
}

