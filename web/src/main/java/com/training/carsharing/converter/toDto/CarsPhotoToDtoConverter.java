package com.training.carsharing.converter.toDto;

import com.training.carsharing.converter.AbstractToDtoConverter;
import com.training.carsharing.dto.CarsPhotoDto;
import com.training.carsharing.model.impl.Car;
import com.training.carsharing.model.impl.CarsPhoto;
import org.springframework.stereotype.Component;

import java.util.function.Function;

@Component
public class CarsPhotoToDtoConverter extends AbstractToDtoConverter<CarsPhoto, CarsPhotoDto> implements Function<CarsPhoto, CarsPhotoDto> {

    protected CarsPhotoToDtoConverter() {
        super(CarsPhotoDto.class);
    }

    @Override
    public CarsPhotoDto apply(final CarsPhoto entity) {
        final CarsPhotoDto dto = applyBaseDto(entity);
        dto.setLink(entity.getLink());

        final Car car = entity.getCar();
        if (car != null) {
            dto.setCarId(car.getId());
            dto.setCarPlate(car.getPlate());
        }
        return dto;
    }

}

