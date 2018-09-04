package com.training.carsharing.converter.fromDto;

import com.training.carsharing.CarService;
import com.training.carsharing.UserAccountService;
import com.training.carsharing.converter.AbstractFromDtoConverter;
import com.training.carsharing.dto.AdDto;
import com.training.carsharing.model.impl.Ad;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.persistence.PersistenceException;
import java.util.function.Function;

@Component
public class AdFromDtoConverter extends AbstractFromDtoConverter<AdDto, Ad> implements Function<AdDto, Ad> {

    @Autowired
    private UserAccountService userAccountService;
    @Autowired
    private CarService carService;

    @Override
    public Ad apply(final AdDto dto) throws PersistenceException {
        Ad entity = applyBaseEntity(dto.getId());
        entity.setCar(carService.findById(dto.getCarId()));
        entity.setUserAccount(userAccountService.findById(dto.getUserAccountId()));
        entity.setAddress(dto.getAddress());
        entity.setPrice(dto.getPrice());
        entity.setBody(dto.getBody());
        entity.setActive(dto.isActive());

        entity.setVersion(dto.getVersion());
        return entity;
    }


}