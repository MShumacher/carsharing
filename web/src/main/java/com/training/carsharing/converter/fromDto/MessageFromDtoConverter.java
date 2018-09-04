package com.training.carsharing.converter.fromDto;

import com.training.carsharing.AdService;
import com.training.carsharing.BrandService;
import com.training.carsharing.UserAccountService;
import com.training.carsharing.converter.AbstractFromDtoConverter;
import com.training.carsharing.dto.MessageDto;
import com.training.carsharing.model.impl.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.persistence.PersistenceException;
import java.util.function.Function;

@Component
public class MessageFromDtoConverter extends AbstractFromDtoConverter<MessageDto, Message> implements Function<MessageDto, Message> {

    @Autowired
    private AdService adService;
    @Autowired
    private UserAccountService userAccountService;

    @Override
    public Message apply(final MessageDto dto) throws PersistenceException {
        Message entity = applyBaseEntity(dto.getId());
        entity.setMessage(dto.getMessage());
        entity.setAd(adService.findById(dto.getAdId()));
        entity.setSender(userAccountService.findById(dto.getSenderId()));
        entity.setRecipient(userAccountService.findById(dto.getRecipientId()));
        entity.setViewed(dto.isViewed());
        entity.setVersion(dto.getVersion());
        return entity;
    }


}