package com.training.carsharing.converter.toDto;

import com.training.carsharing.converter.AbstractToDtoConverter;
import com.training.carsharing.dto.FuelDto;
import com.training.carsharing.model.impl.Fuel;
import org.springframework.stereotype.Component;

import java.util.function.Function;

@Component
public class FuelToDtoConverter extends AbstractToDtoConverter<Fuel, FuelDto> implements Function<Fuel, FuelDto> {

    protected FuelToDtoConverter() {
        super(FuelDto.class);
    }

    @Override
    public FuelDto apply(final Fuel entity) {
        final FuelDto dto = applyBaseDto(entity);
        dto.setName(entity.getName());
        return dto;
    }
}

