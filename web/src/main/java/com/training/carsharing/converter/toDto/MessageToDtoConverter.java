package com.training.carsharing.converter.toDto;

import com.training.carsharing.converter.AbstractToDtoConverter;
import com.training.carsharing.dto.MessageDto;
import com.training.carsharing.model.impl.Ad;
import com.training.carsharing.model.impl.UserAccount;
import com.training.carsharing.model.impl.Message;
import org.springframework.stereotype.Component;

import java.util.function.Function;

@Component
public class MessageToDtoConverter extends AbstractToDtoConverter<Message, MessageDto> implements Function<Message, MessageDto> {

    protected MessageToDtoConverter() {
        super(MessageDto.class);
    }

    @Override
    public MessageDto apply(final Message entity) {
        final MessageDto dto = applyBaseDto(entity);
        dto.setMessage(entity.getMessage());

        final Ad ad = entity.getAd();
        if (ad != null) {
            dto.setAdId(ad.getId());
        }

        final UserAccount sender = entity.getSender();
        if (sender != null) {
            dto.setSenderId(sender.getId());
            dto.setSenderEmail(sender.getEmail());
        }
        final UserAccount recipient = entity.getRecipient();
        if (recipient != null) {
            dto.setRecipientId(recipient.getId());
            dto.setRecipientEmail(recipient.getEmail());
        }

        dto.setViewed(entity.isViewed());
        return dto;
    }

}

