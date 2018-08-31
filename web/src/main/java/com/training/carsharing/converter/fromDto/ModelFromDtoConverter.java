package com.training.carsharing.converter.fromDto;

import com.training.carsharing.BrandService;
import com.training.carsharing.converter.AbstractFromDtoConverter;
import com.training.carsharing.dto.ModelDto;
import com.training.carsharing.model.impl.Brand;
import com.training.carsharing.model.impl.Model;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.persistence.PersistenceException;
import java.util.function.Function;

@Component
public class ModelFromDtoConverter extends AbstractFromDtoConverter<ModelDto, Model> implements Function<ModelDto, Model> {

    @Autowired
    private BrandService brandService;

    @Override
    public Model apply(final ModelDto dto) throws PersistenceException {
        Model entity = applyBaseEntity(dto.getId());
        entity.setName(dto.getName());

        Brand brand = brandService.findById(dto.getBrandId());
        entity.setBrand(brand);

        entity.setVersion(dto.getVersion());
        return entity;
    }


}