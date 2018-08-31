package com.training.carsharing.converter.toDto;

import com.training.carsharing.converter.AbstractToDtoConverter;
import com.training.carsharing.dto.PassportDto;
import com.training.carsharing.model.impl.UserAccount;
import com.training.carsharing.model.impl.Passport;
import org.springframework.stereotype.Component;

import java.util.function.Function;

@Component
public class PassportToDtoConverter extends AbstractToDtoConverter<Passport, PassportDto> implements Function<Passport, PassportDto> {

    protected PassportToDtoConverter() {
        super(PassportDto.class);
    }

    @Override
    public PassportDto apply(final Passport entity) {
        final PassportDto dto = applyBaseDto(entity);
        dto.setFullName(entity.getFullName());
        dto.setNumber(entity.getNumber());
        dto.setIssuePlace(entity.getIssuePlace());
        dto.setIssueDate(entity.getIssueDate());
        dto.setBirthPlace(entity.getBirthPlace());
        dto.setBirthday(entity.getBirthday());

        final UserAccount userAccount = entity.getUserAccount();
        if (userAccount != null) {
            dto.setUserAccountId(userAccount.getId());
            dto.setUserAccountEmail(userAccount.getEmail());
        }
        return dto;
    }
}

