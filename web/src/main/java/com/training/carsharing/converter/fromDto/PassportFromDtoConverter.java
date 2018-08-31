package com.training.carsharing.converter.fromDto;

import com.training.carsharing.UserAccountService;
import com.training.carsharing.converter.AbstractFromDtoConverter;
import com.training.carsharing.dto.PassportDto;
import com.training.carsharing.model.impl.Passport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.persistence.PersistenceException;
import java.util.function.Function;

@Component
public class PassportFromDtoConverter extends AbstractFromDtoConverter<PassportDto, Passport> implements Function<PassportDto, Passport> {

    @Autowired
    private UserAccountService userAccountService;
    
    @Override
    public Passport apply(final PassportDto dto) throws PersistenceException {
        Passport entity = applyBaseEntity(dto.getId());
        entity.setFullName(dto.getFullName());
        entity.setNumber(dto.getNumber());
        entity.setIssuePlace(dto.getIssuePlace());
        entity.setIssueDate(dto.getIssueDate());
        entity.setBirthPlace(dto.getBirthPlace());
        entity.setBirthday(dto.getBirthday());
        entity.setUserAccount(userAccountService.findById(dto.getUserAccountId()));
        entity.setVersion(dto.getVersion());
        return entity;
    }


}