package com.training.carsharing.converter.toDto;

import com.training.carsharing.converter.AbstractToDtoConverter;
import com.training.carsharing.dto.BaseDto;
import com.training.carsharing.dto.BrandDto;
import com.training.carsharing.dto.ModelDto;
import com.training.carsharing.model.impl.Brand;
import com.training.carsharing.model.impl.Model;
import org.springframework.stereotype.Component;

import java.util.function.Function;

@Component
public class ModelToDtoConverter extends AbstractToDtoConverter<Model, ModelDto> implements Function<Model, ModelDto> {

    protected ModelToDtoConverter() {
        super(ModelDto.class);
    }

    @Override
    public ModelDto apply(final Model entity) {
        final ModelDto dto = applyBaseDto(entity);
        dto.setName(entity.getName());

        final Brand brand = entity.getBrand();
        if (brand != null) {
            dto.setBrandId(brand.getId());
            dto.setBrandName(brand.getName());
        }
        return dto;
    }

}

