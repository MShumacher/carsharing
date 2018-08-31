package com.training.carsharing.converter.toDto;

import com.training.carsharing.converter.AbstractToDtoConverter;
import com.training.carsharing.dto.BodyTypeDto;
import com.training.carsharing.model.impl.BodyType;
import org.springframework.stereotype.Component;

import java.util.function.Function;

@Component
public class BodyTypeToDtoConverter extends AbstractToDtoConverter<BodyType, BodyTypeDto> implements Function<BodyType, BodyTypeDto> {

    protected BodyTypeToDtoConverter() {
        super(BodyTypeDto.class);
    }

    @Override
    public BodyTypeDto apply(final BodyType entity) {
        final BodyTypeDto brandDto = applyBaseDto(entity);
        brandDto.setName(entity.getName());
        return brandDto;
    }
}

