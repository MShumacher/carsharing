package com.training.carsharing.converter.fromDto;

import com.training.carsharing.UserAccountService;
import com.training.carsharing.converter.AbstractFromDtoConverter;
import com.training.carsharing.dto.DrivingLicenseDto;
import com.training.carsharing.model.impl.DrivingLicense;
import com.training.carsharing.model.impl.UserAccount;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.persistence.PersistenceException;
import java.util.function.Function;

@Component
public class DrivingLicenseFromDtoConverter extends AbstractFromDtoConverter<DrivingLicenseDto, DrivingLicense> implements Function<DrivingLicenseDto, DrivingLicense> {

    @Autowired
    private UserAccountService userAccountService;

    @Override
    public DrivingLicense apply(final DrivingLicenseDto dto) throws PersistenceException {
        DrivingLicense entity = applyBaseEntity(dto.getId());
        entity.setNumber(dto.getNumber());
        entity.setExpirationDate(dto.getExpirationDate());
        entity.setCategories(dto.getCategories());
        entity.setUserAccount(userAccountService.findById(dto.getUserAccountId()));
        entity.setVersion(dto.getVersion());
        return entity;
    }


}