package com.training.carsharing.converter.fromDto;

import com.training.carsharing.converter.AbstractFromDtoConverter;
import com.training.carsharing.dto.CarParameterDto;
import com.training.carsharing.model.impl.CarParameter;
import org.springframework.stereotype.Component;

import javax.persistence.PersistenceException;
import java.util.function.Function;

@Component
public class CarParameterFromDtoConverter extends AbstractFromDtoConverter<CarParameterDto, CarParameter> implements Function<CarParameterDto, CarParameter> {

    @Override
    public CarParameter apply(final CarParameterDto dto) throws PersistenceException {
        final CarParameter entity = applyBaseEntity(dto.getId());
        entity.setName(dto.getName());
        entity.setVersion(dto.getVersion());
        return entity;
    }
}