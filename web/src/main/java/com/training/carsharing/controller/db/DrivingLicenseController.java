package com.training.carsharing.controller.db;

import com.training.carsharing.dto.DrivingLicenseDto;
import com.training.carsharing.model.impl.DrivingLicense;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping(value = "/drivinglicense")
public class DrivingLicenseController extends AbstractController<DrivingLicense, DrivingLicenseDto, Long> {

    protected DrivingLicenseController() {
        super(DrivingLicenseDto.class);
    }

    @Override
    public Map<String, Object> getHashMapWithAllCommonForms(Long id) {
        final Map<String, Object> hashMap = new HashMap<>();
        loadCommonFormUserAccountsWithCurrentDrivingLicenseOrWithout(hashMap, id);
        return hashMap;
    }
}

