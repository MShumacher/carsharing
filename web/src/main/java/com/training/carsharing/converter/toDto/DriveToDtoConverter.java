package com.training.carsharing.converter.toDto;

import com.training.carsharing.converter.AbstractToDtoConverter;
import com.training.carsharing.dto.DriveDto;
import com.training.carsharing.model.impl.Drive;
import org.springframework.stereotype.Component;

import java.util.function.Function;

@Component
public class DriveToDtoConverter extends AbstractToDtoConverter<Drive, DriveDto> implements Function<Drive, DriveDto> {

    protected DriveToDtoConverter() {
        super(DriveDto.class);
    }

    @Override
    public DriveDto apply(final Drive entity) {
        final DriveDto dto = applyBaseDto(entity);
        dto.setName(entity.getName());
        return dto;
    }
}

