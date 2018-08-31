package com.training.carsharing.converter.fromDto;

import com.training.carsharing.converter.AbstractFromDtoConverter;
import com.training.carsharing.dto.GearboxDto;
import com.training.carsharing.model.impl.Gearbox;
import org.springframework.stereotype.Component;

import javax.persistence.PersistenceException;
import java.util.function.Function;

@Component
public class GearboxFromDtoConverter extends AbstractFromDtoConverter<GearboxDto, Gearbox> implements Function<GearboxDto, Gearbox> {

    @Override
    public Gearbox apply(final GearboxDto dto) throws PersistenceException {
        final Gearbox entity = applyBaseEntity(dto.getId());
        entity.setName(dto.getName());
        entity.setVersion(dto.getVersion());
        return entity;
    }
}