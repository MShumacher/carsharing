package com.training.carsharing.converter.toDto;

import com.training.carsharing.converter.AbstractToDtoConverter;
import com.training.carsharing.dto.UserAccountDto;
import com.training.carsharing.model.impl.DrivingLicense;
import com.training.carsharing.model.impl.Passport;
import com.training.carsharing.model.impl.UserAccount;
import org.springframework.stereotype.Component;

import java.util.function.Function;

@Component
public class UserAccountToDtoConverter extends AbstractToDtoConverter<UserAccount, UserAccountDto> implements Function<UserAccount, UserAccountDto> {

    protected UserAccountToDtoConverter() {
        super(UserAccountDto.class);
    }

    @Override
    public UserAccountDto apply(final UserAccount entity) {
        final UserAccountDto dto = applyBaseDto(entity);
        dto.setName(entity.getName());
        dto.setEmail(entity.getEmail());
        dto.setPassword(entity.getPassword());
        dto.setPhone(entity.getPhone());
        dto.setPhotoLink(entity.getPhotoLink());
        dto.setRole(entity.getRole().name());

        final Passport passport = entity.getPassport();
        if (passport != null) {
            dto.setPassportId(passport.getId());
            dto.setPassportNumber(passport.getNumber());
        }

        final DrivingLicense drivingLicense = entity.getDrivingLicense();
        if (drivingLicense != null) {
            dto.setDrivingLicenseId(drivingLicense.getId());
            dto.setDrivingLicenseNumber(drivingLicense.getNumber());
        }
        return dto;
    }
}

