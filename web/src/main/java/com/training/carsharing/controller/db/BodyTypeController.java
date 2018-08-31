package com.training.carsharing.controller.db;

import com.training.carsharing.dto.BodyTypeDto;
import com.training.carsharing.model.impl.BodyType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping(value = "/bodytype")
public class BodyTypeController extends AbstractController<BodyType, BodyTypeDto, Long> {

    protected BodyTypeController() {
        super(BodyTypeDto.class);
    }

    @Override
    public Map<String, Object> getHashMapWithAllCommonForms(Long id) {
        final Map<String, Object> hashMap = new HashMap<>();
        return hashMap;
    }
}

