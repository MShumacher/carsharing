package com.training.carsharing.converter.fromDto;

import com.training.carsharing.CarService;
import com.training.carsharing.converter.AbstractFromDtoConverter;
import com.training.carsharing.dto.CarsPhotoDto;
import com.training.carsharing.model.impl.CarsPhoto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.persistence.PersistenceException;
import java.util.function.Function;

@Component
public class CarsPhotoFromDtoConverter extends AbstractFromDtoConverter<CarsPhotoDto, CarsPhoto> implements Function<CarsPhotoDto, CarsPhoto> {

    @Autowired
    private CarService carService;

    @Override
    public CarsPhoto apply(final CarsPhotoDto dto) throws PersistenceException {
        CarsPhoto entity = applyBaseEntity(dto.getId());
        entity.setLink(dto.getLink());
        entity.setCar(carService.findById(dto.getCarId()));
        entity.setVersion(dto.getVersion());
        return entity;
    }


}