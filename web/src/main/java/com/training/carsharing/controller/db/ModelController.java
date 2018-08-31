package com.training.carsharing.controller.db;

import com.training.carsharing.dto.ModelDto;
import com.training.carsharing.model.impl.Model;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping(value = "/model")
public class ModelController extends AbstractController<Model, ModelDto, Long> {

    protected ModelController() {
        super(ModelDto.class);
    }

    @Override
    public Map<String, Object> getHashMapWithAllCommonForms(Long id) {
        final Map<String, Object> hashMap = new HashMap<>();
        loadCommonFormBrands(hashMap);
        return hashMap;
    }
}

