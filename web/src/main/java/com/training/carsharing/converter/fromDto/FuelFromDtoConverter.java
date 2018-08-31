package com.training.carsharing.converter.fromDto;

import com.training.carsharing.converter.AbstractFromDtoConverter;
import com.training.carsharing.dto.FuelDto;
import com.training.carsharing.model.impl.Fuel;
import org.springframework.stereotype.Component;

import javax.persistence.PersistenceException;
import java.util.function.Function;

@Component
public class FuelFromDtoConverter extends AbstractFromDtoConverter<FuelDto, Fuel> implements Function<FuelDto, Fuel> {

    @Override
    public Fuel apply(final FuelDto dto) throws PersistenceException {
        final Fuel entity = applyBaseEntity(dto.getId());
        entity.setName(dto.getName());
        entity.setVersion(dto.getVersion());
        return entity;
    }
}