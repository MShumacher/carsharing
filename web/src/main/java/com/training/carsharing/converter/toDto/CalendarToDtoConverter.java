package com.training.carsharing.converter.toDto;

import com.training.carsharing.converter.AbstractToDtoConverter;
import com.training.carsharing.dto.CalendarDto;
import com.training.carsharing.model.impl.Car;
import com.training.carsharing.model.impl.UserAccount;
import com.training.carsharing.model.impl.Calendar;
import com.training.carsharing.model.impl.UserAccount;
import org.springframework.stereotype.Component;

import java.util.function.Function;

@Component
public class CalendarToDtoConverter extends AbstractToDtoConverter<Calendar, CalendarDto> implements Function<Calendar, CalendarDto> {

    protected CalendarToDtoConverter() {
        super(CalendarDto.class);
    }

    @Override
    public CalendarDto apply(final Calendar entity) {
        final CalendarDto dto = applyBaseDto(entity);

        final UserAccount renter = entity.getRenter();
        if (renter != null) {
            dto.setRenterId(renter.getId());
            dto.setRenterEmail(renter.getEmail());
        }

        final Car car = entity.getCar();
        if (car != null) {
            dto.setCarId(car.getId());
            dto.setCarPlate(car.getPlate());
        }

        dto.setStart(entity.getStart());
        dto.setEnd(entity.getEnd());
        dto.setTotalPrice(entity.getTotalPrice());
        return dto;
    }

}

