package com.training.carsharing.converter.toDto;

import com.training.carsharing.converter.AbstractToDtoConverter;
import com.training.carsharing.dto.CarParameterDto;
import com.training.carsharing.model.impl.CarParameter;
import org.springframework.stereotype.Component;

import java.util.function.Function;

@Component
public class CarParameterToDtoConverter extends AbstractToDtoConverter<CarParameter, CarParameterDto> implements Function<CarParameter, CarParameterDto> {

    protected CarParameterToDtoConverter() {
        super(CarParameterDto.class);
    }

    @Override
    public CarParameterDto apply(final CarParameter entity) {
        final CarParameterDto dto = applyBaseDto(entity);
        dto.setName(entity.getName());
        return dto;
    }
}

