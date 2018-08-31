package com.training.carsharing.converter.fromDto;

import com.training.carsharing.converter.AbstractFromDtoConverter;
import com.training.carsharing.dto.DriveDto;
import com.training.carsharing.model.impl.Drive;
import org.springframework.stereotype.Component;

import javax.persistence.PersistenceException;
import java.util.function.Function;

@Component
public class DriveFromDtoConverter extends AbstractFromDtoConverter<DriveDto, Drive> implements Function<DriveDto, Drive> {

    @Override
    public Drive apply(final DriveDto dto) throws PersistenceException {
        final Drive entity = applyBaseEntity(dto.getId());
        entity.setName(dto.getName());
        entity.setVersion(dto.getVersion());
        return entity;
    }
}