package com.training.carsharing.controller.db;

import com.training.carsharing.dto.EngineTypeDto;
import com.training.carsharing.model.impl.EngineType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping(value = "/enginetype")
public class EngineTypeController extends AbstractController<EngineType, EngineTypeDto, Long> {

    protected EngineTypeController() {
        super(EngineTypeDto.class);
    }

    @Override
    public Map<String, Object> getHashMapWithAllCommonForms(Long id) {
        final Map<String, Object> hashMap = new HashMap<>();
        loadCommonFormFuels(hashMap);
        return hashMap;
    }
}

