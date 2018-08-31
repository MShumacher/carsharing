package com.training.carsharing.controller.db;

import com.training.carsharing.dto.PassportDto;
import com.training.carsharing.model.impl.Passport;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping(value = "/passport")
public class PassportController extends AbstractController<Passport, PassportDto, Long> {

    protected PassportController() {
        super(PassportDto.class);
    }

    @Override
    public Map<String, Object> getHashMapWithAllCommonForms(Long id) {
        final Map<String, Object> hashMap = new HashMap<>();
        loadCommonFormUserAccountsWithCurrentPassportOrWithout(hashMap, id);
        return hashMap;
    }
}

