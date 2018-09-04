package com.training.carsharing.converter.toDto;

import com.training.carsharing.converter.AbstractToDtoConverter;
import com.training.carsharing.dto.AdDto;
import com.training.carsharing.model.impl.Ad;
import com.training.carsharing.model.impl.Car;
import com.training.carsharing.model.impl.UserAccount;
import org.springframework.stereotype.Component;

import java.util.function.Function;

@Component
public class AdToDtoConverter extends AbstractToDtoConverter<Ad, AdDto> implements Function<Ad, AdDto> {

    protected AdToDtoConverter() {
        super(AdDto.class);
    }

    @Override
    public AdDto apply(final Ad entity) {
        final AdDto dto = applyBaseDto(entity);

        final Car car = entity.getCar();
        if (car != null) {
            dto.setCarId(car.getId());
            dto.setCarPlate(car.getPlate());
        }

        final UserAccount userAccount = entity.getUserAccount();
        if (userAccount != null) {
            dto.setUserAccountId(userAccount.getId());
            dto.setUserAccountEmail(userAccount.getEmail());
        }

        dto.setAddress(entity.getAddress());
        dto.setPrice(entity.getPrice());
        dto.setBody(entity.getBody());
        dto.setActive(entity.isActive());
        return dto;
    }
}

