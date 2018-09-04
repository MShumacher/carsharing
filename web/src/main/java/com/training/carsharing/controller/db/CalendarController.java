package com.training.carsharing.controller.db;

import com.training.carsharing.dto.CalendarDto;
import com.training.carsharing.model.impl.Calendar;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping(value = "/calendar")
public class CalendarController extends AbstractController<Calendar, CalendarDto, Long> {

    protected CalendarController() {
        super(CalendarDto.class);
    }

    @Override
    public Map<String, Object> getHashMapWithAllCommonForms(Long id) {
        final Map<String, Object> hashMap = new HashMap<>();
        loadCommonFormCars(hashMap);
        loadCommonFormAllUserAccounts(hashMap);
        return hashMap;
    }
}

