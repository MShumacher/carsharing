package com.training.carsharing.converter.fromDto;

import com.training.carsharing.*;
import com.training.carsharing.converter.AbstractFromDtoConverter;
import com.training.carsharing.dto.CarDto;
import com.training.carsharing.model.impl.Car;
import com.training.carsharing.model.impl.CarParameter;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.persistence.PersistenceException;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

@Component
public class CarFromDtoConverter extends AbstractFromDtoConverter<CarDto, Car> implements Function<CarDto, Car> {

    @Autowired
    private ModelService modelService;
    @Autowired
    private GearboxService gearboxService;
    @Autowired
    private BodyTypeService bodyTypeService;
    @Autowired
    private DriveService driveService;
    @Autowired
    private EngineTypeService engineTypeService;
    @Autowired
    private CarParameterService carParameterService;

    @Override
    public Car apply(final CarDto dto) throws PersistenceException {
        Car entity = applyBaseEntity(dto.getId());
        entity.setModel(modelService.findById(dto.getModelId()));
        entity.setYear(dto.getYear());
        entity.setPlate(dto.getPlate());
        entity.setMileage(dto.getMileage());
        entity.setSeats(dto.getSeats());
        entity.setGearbox(gearboxService.findById(dto.getGearboxId()));
        entity.setBodyType(bodyTypeService.findById(dto.getBodyTypeId()));
        entity.setDrive(driveService.findById(dto.getDriveId()));
        entity.setEngineType(engineTypeService.findById(dto.getEngineTypeId()));
        entity.setCharge(dto.getCharge());
        entity.setConditions(dto.getConditions());
        entity.setInsurance(dto.getInsurance());

        final Set<Long> carParameterIds = dto.getCarParameterIds();
        if (CollectionUtils.isNotEmpty(carParameterIds)) {
            entity.setCarParameter(carParameterIds.stream().map((id) -> carParameterService.findById(id)).collect(Collectors.toSet()));
        }

        entity.setVersion(dto.getVersion());
        return entity;
    }


}