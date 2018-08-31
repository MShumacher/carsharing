package com.training.carsharing.converter.fromDto;

import com.training.carsharing.FuelService;
import com.training.carsharing.converter.AbstractFromDtoConverter;
import com.training.carsharing.dto.EngineTypeDto;
import com.training.carsharing.model.impl.Fuel;
import com.training.carsharing.model.impl.EngineType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.persistence.PersistenceException;
import java.util.function.Function;

@Component
public class EngineTypeFromDtoConverter extends AbstractFromDtoConverter<EngineTypeDto, EngineType> implements Function<EngineTypeDto, EngineType> {

    @Autowired
    private FuelService fuelService;

    @Override
    public EngineType apply(final EngineTypeDto dto) throws PersistenceException {
        EngineType entity = applyBaseEntity(dto.getId());
        entity.setName(dto.getName());

        Fuel brand = fuelService.findById(dto.getFuelId());
        entity.setFuel(brand);

        entity.setVersion(dto.getVersion());
        return entity;
    }


}