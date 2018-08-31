package com.training.carsharing.converter.fromDto;

import com.training.carsharing.converter.AbstractFromDtoConverter;
import com.training.carsharing.dto.BrandDto;
import com.training.carsharing.model.impl.Brand;
import org.springframework.stereotype.Component;

import javax.persistence.PersistenceException;
import java.util.function.Function;

@Component
public class BrandFromDtoConverter extends AbstractFromDtoConverter<BrandDto, Brand> implements Function<BrandDto, Brand> {

    @Override
    public Brand apply(final BrandDto dto) throws PersistenceException {
        final Brand entity = applyBaseEntity(dto.getId());
        entity.setName(dto.getName());
        entity.setVersion(dto.getVersion());
        return entity;
    }
}