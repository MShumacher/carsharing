package com.training.carsharing.converter.toDto;

import com.training.carsharing.converter.AbstractToDtoConverter;
import com.training.carsharing.dto.CarDto;
import com.training.carsharing.model.impl.*;
import org.springframework.stereotype.Component;

import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

@Component
public class CarToDtoConverter extends AbstractToDtoConverter<Car, CarDto> implements Function<Car, CarDto> {

    protected CarToDtoConverter() {
        super(CarDto.class);
    }

    @Override
    public CarDto apply(final Car entity) {
        final CarDto dto = applyBaseDto(entity);

        final Model model = entity.getModel();
        if (model != null) {
            dto.setModelId(model.getId());
            dto.setModelName(model.getName());
            final Brand brand = model.getBrand();
            dto.setBrandId(brand.getId());
            dto.setBrandName(brand.getName());
        }

        dto.setYear(entity.getYear());
        dto.setPlate(entity.getPlate());
        dto.setMileage(entity.getMileage());
        dto.setSeats(entity.getSeats());

        final Gearbox gearbox = entity.getGearbox();
        if (gearbox != null) {
            dto.setGearboxId(gearbox.getId());
            dto.setGearboxName(gearbox.getName());
        }

        final BodyType bodyType = entity.getBodyType();
        if (bodyType != null) {
            dto.setBodyTypeId(bodyType.getId());
            dto.setBodyTypeName(bodyType.getName());
        }

        final Drive drive = entity.getDrive();
        if (drive != null) {
            dto.setDriveId(drive.getId());
            dto.setDriveName(drive.getName());
        }

        final EngineType engineType = entity.getEngineType();
        if (engineType != null) {
            dto.setEngineTypeId(engineType.getId());
            dto.setEngineTypeName(engineType.getName());
        }

        dto.setCharge(entity.getCharge());
        dto.setConditions(entity.getConditions());
        dto.setInsurance(entity.getInsurance());

        final Set<CarParameter> carParameters = entity.getCarParameter();
        if (carParameters != null) {
            dto.setCarParameterIds(carParameters.stream().map(CarParameter::getId).collect(Collectors.toSet()));
        }

        return dto;
    }

}

