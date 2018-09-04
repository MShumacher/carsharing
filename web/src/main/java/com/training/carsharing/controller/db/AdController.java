package com.training.carsharing.controller.db;

import com.training.carsharing.dto.AdDto;
import com.training.carsharing.model.impl.Ad;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping(value = "/ad")
public class AdController extends AbstractController<Ad, AdDto, Long> {

    protected AdController() {
        super(AdDto.class);
    }

    @Override
    public Map<String, Object> getHashMapWithAllCommonForms(Long id) {
        final Map<String, Object> hashMap = new HashMap<>();
        loadCommonFormAllUserAccounts(hashMap);
        loadCommonFormCarsWithCurrentAdOrWithout(hashMap, id);
        return hashMap;
    }
}

