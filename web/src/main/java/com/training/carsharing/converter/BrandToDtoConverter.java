package com.training.carsharing.converter;

import com.training.carsharing.dto.BrandDto;
import com.training.carsharing.model.impl.Brand;
import org.springframework.stereotype.Component;

import java.util.function.Function;

@Component
public class BrandToDtoConverter implements Function<Brand, BrandDto> {

        @Override
        public BrandDto apply(final Brand entity) {
            final BrandDto brandDto = new BrandDto();
            brandDto.setId(entity.getId());
            brandDto.setName(entity.getName());
            brandDto.setCreatedDate(entity.getCreatedDate().get());
            //brandDto.setCreatedBy(entity.getCreatedBy());
            brandDto.setLastModifiedDate(entity.getLastModifiedDate().get());
            //brandDto.setLastModifiedBy(entity.getLastModifiedBy());
            return brandDto;
        }

    }

