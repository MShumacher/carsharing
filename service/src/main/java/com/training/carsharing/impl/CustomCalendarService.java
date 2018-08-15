package com.training.carsharing.impl;

import com.training.carsharing.CalendarService;
import com.training.carsharing.model.impl.Calendar;
import com.training.carsharing.repository.CalendarRepository;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

@Service
public class CustomCalendarService extends CustomAbstractService<Calendar, CalendarRepository, Integer> implements CalendarService {

    public CustomCalendarService() {
        super(Calendar.class);
    }
}
