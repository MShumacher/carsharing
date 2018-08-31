package com.training.carsharing.converter.toDto;

import com.training.carsharing.converter.AbstractToDtoConverter;
import com.training.carsharing.dto.EngineTypeDto;
import com.training.carsharing.model.impl.Fuel;
import com.training.carsharing.model.impl.EngineType;
import org.springframework.stereotype.Component;

import java.util.function.Function;

@Component
public class EngineTypeToDtoConverter extends AbstractToDtoConverter<EngineType, EngineTypeDto> implements Function<EngineType, EngineTypeDto> {

    protected EngineTypeToDtoConverter() {
        super(EngineTypeDto.class);
    }

    @Override
    public EngineTypeDto apply(final EngineType entity) {
        final EngineTypeDto dto = applyBaseDto(entity);
        dto.setName(entity.getName());

        final Fuel brand = entity.getFuel();
        if (brand != null) {
            dto.setFuelId(brand.getId());
            dto.setFuelName(brand.getName());
        }
        return dto;
    }

}

