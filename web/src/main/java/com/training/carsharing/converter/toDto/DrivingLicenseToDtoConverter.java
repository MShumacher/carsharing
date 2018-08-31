package com.training.carsharing.converter.toDto;

import com.training.carsharing.converter.AbstractToDtoConverter;
import com.training.carsharing.dto.DrivingLicenseDto;
import com.training.carsharing.model.impl.DrivingLicense;
import com.training.carsharing.model.impl.UserAccount;
import org.springframework.stereotype.Component;

import java.util.function.Function;

@Component
public class DrivingLicenseToDtoConverter extends AbstractToDtoConverter<DrivingLicense, DrivingLicenseDto> implements Function<DrivingLicense, DrivingLicenseDto> {

    protected DrivingLicenseToDtoConverter() {
        super(DrivingLicenseDto.class);
    }

    @Override
    public DrivingLicenseDto apply(final DrivingLicense entity) {
        final DrivingLicenseDto dto = applyBaseDto(entity);
        dto.setNumber(entity.getNumber());
        dto.setExpirationDate(entity.getExpirationDate());
        dto.setCategories(entity.getCategories());

        final UserAccount userAccount = entity.getUserAccount();
        if (userAccount != null) {
            dto.setUserAccountId(userAccount.getId());
            dto.setUserAccountEmail(userAccount.getEmail());
        }
        return dto;
    }
}

