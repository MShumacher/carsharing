package com.training.carsharing.converter.fromDto;

import com.training.carsharing.AdService;
import com.training.carsharing.CarService;
import com.training.carsharing.UserAccountService;
import com.training.carsharing.converter.AbstractFromDtoConverter;
import com.training.carsharing.dto.CalendarDto;
import com.training.carsharing.model.impl.Calendar;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.persistence.PersistenceException;
import java.util.function.Function;

@Component
public class CalendarFromDtoConverter extends AbstractFromDtoConverter<CalendarDto, Calendar> implements Function<CalendarDto, Calendar> {

    @Autowired
    private CarService carService;
    @Autowired
    private UserAccountService userAccountService;

    @Override
    public Calendar apply(final CalendarDto dto) throws PersistenceException {
        Calendar entity = applyBaseEntity(dto.getId());
        entity.setRenter(userAccountService.findById(dto.getRenterId()));
        entity.setCar(carService.findById(dto.getCarId()));
        entity.setStart(dto.getStart());
        entity.setEnd(dto.getEnd());
        entity.setTotalPrice(dto.getTotalPrice());
        entity.setVersion(dto.getVersion());
        return entity;
    }


}