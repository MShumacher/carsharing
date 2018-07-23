package com.training.carsharing.impl;

import com.training.carsharing.ICalendarService;
import com.training.carsharing.dao.ICalendarDao;
import com.training.carsharing.model.ICalendar;
import com.training.carsharing.model.impl.Calendar;
import org.springframework.stereotype.Service;

@Service
public class CalendarServiceImpl extends AbstractServiceImpl<ICalendar, ICalendarDao, Integer> implements ICalendarService {

    public CalendarServiceImpl() {
        super(Calendar.class);
    }
}
