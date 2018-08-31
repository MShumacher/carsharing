package com.training.carsharing.converter.fromDto;

import com.training.carsharing.converter.AbstractFromDtoConverter;
import com.training.carsharing.dto.BodyTypeDto;
import com.training.carsharing.model.impl.BodyType;
import org.springframework.stereotype.Component;

import javax.persistence.PersistenceException;
import java.util.function.Function;

@Component
public class BodyTypeFromDtoConverter extends AbstractFromDtoConverter<BodyTypeDto, BodyType> implements Function<BodyTypeDto, BodyType> {

    @Override
    public BodyType apply(final BodyTypeDto dto) throws PersistenceException {
        final BodyType entity = applyBaseEntity(dto.getId());
        entity.setName(dto.getName());
        entity.setVersion(dto.getVersion());
        return entity;
    }
}