package com.training.carsharing.converter.toDto;

import com.training.carsharing.converter.AbstractToDtoConverter;
import com.training.carsharing.dto.BaseDto;
import com.training.carsharing.dto.BrandDto;
import com.training.carsharing.model.impl.Brand;
import org.springframework.stereotype.Component;

import java.util.function.Function;

@Component
public class BrandToDtoConverter extends AbstractToDtoConverter<Brand, BrandDto> implements Function<Brand, BrandDto> {

    protected BrandToDtoConverter() {
        super(BrandDto.class);
    }

    @Override
    public BrandDto apply(final Brand entity) {
        final BrandDto dto = applyBaseDto(entity);
        dto.setName(entity.getName());
        return dto;
    }
}

