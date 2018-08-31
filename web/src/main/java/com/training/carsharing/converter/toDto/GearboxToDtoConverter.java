package com.training.carsharing.converter.toDto;

import com.training.carsharing.converter.AbstractToDtoConverter;
import com.training.carsharing.dto.GearboxDto;
import com.training.carsharing.model.impl.Gearbox;
import org.springframework.stereotype.Component;

import java.util.function.Function;

@Component
public class GearboxToDtoConverter extends AbstractToDtoConverter<Gearbox, GearboxDto> implements Function<Gearbox, GearboxDto> {

    protected GearboxToDtoConverter() {
        super(GearboxDto.class);
    }

    @Override
    public GearboxDto apply(final Gearbox entity) {
        final GearboxDto dto = applyBaseDto(entity);
        dto.setName(entity.getName());
        return dto;
    }
}

