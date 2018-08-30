package com.training.carsharing.converter;

import com.training.carsharing.BrandService;
import com.training.carsharing.dto.BrandDto;
import com.training.carsharing.model.impl.Brand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.persistence.PersistenceException;
import java.util.function.Function;

@Component
public class BrandFromDtoConverter implements Function<BrandDto, Brand> {

    @Autowired
    private BrandService brandService;

    @Override
    public Brand apply(final BrandDto dto) throws PersistenceException {
        Brand entity = brandService.createEntity();
        if (dto.getId() != null) {
            Brand entityFromDb = brandService.findById(dto.getId());
            if (entityFromDb == null) {
                // случай, когда кто-то удалил сущность, которую мы пытаемся перезаписать
                throw new PersistenceException();
            } else {
                entity.setId(entityFromDb.getId());
                entity.setCreatedDate(entityFromDb.getCreatedDate().get());
                entity.setCreatedBy(entityFromDb.getCreatedBy().get());
                entity.setLastModifiedDate(entityFromDb.getLastModifiedDate().get());
                entity.setLastModifiedBy(entityFromDb.getLastModifiedBy().get());
            }
        }
        entity.setName(dto.getName());
        entity.setVersion(dto.getVersion());
        return entity;
    }
}