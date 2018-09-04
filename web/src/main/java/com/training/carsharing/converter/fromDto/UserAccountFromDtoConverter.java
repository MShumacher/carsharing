package com.training.carsharing.converter.fromDto;

import com.training.carsharing.DrivingLicenseService;
import com.training.carsharing.PassportService;
import com.training.carsharing.converter.AbstractFromDtoConverter;
import com.training.carsharing.dto.UserAccountDto;
import com.training.carsharing.model.enums.Role;
import com.training.carsharing.model.impl.DrivingLicense;
import com.training.carsharing.model.impl.Passport;
import com.training.carsharing.model.impl.UserAccount;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.persistence.PersistenceException;
import java.util.function.Function;

@Component
public class UserAccountFromDtoConverter extends AbstractFromDtoConverter<UserAccountDto, UserAccount> implements Function<UserAccountDto, UserAccount> {

    @Autowired
    private PassportService passportService;
    @Autowired
    private DrivingLicenseService drivingLicenseService;

    @Override
    public UserAccount apply(final UserAccountDto dto) throws PersistenceException {
        UserAccount entity = applyBaseEntity(dto.getId());
        entity.setEmail(dto.getEmail());
        entity.setPassword(dto.getPassword());
        entity.setName(dto.getName());
        entity.setPhone(dto.getPhone());
        entity.setPhotoLink(dto.getPhotoLink());

        if (dto.getRole() != null) {
            entity.setRole(Role.valueOf(dto.getRole()));
        } else{
            entity.setRole(Role.valueOf("ROLE_USER"));
        }

        if (dto.getPassportId() != null) {
            entity.setPassport(passportService.findById(dto.getPassportId()));
        }

        if (dto.getDrivingLicenseId() != null) {
            entity.setDrivingLicense(drivingLicenseService.findById(dto.getDrivingLicenseId()));
        }

        entity.setVerifyKey(dto.getVerifyKey());
        entity.setVerified(dto.isVerified());
        entity.setVersion(dto.getVersion());
        return entity;
    }


}