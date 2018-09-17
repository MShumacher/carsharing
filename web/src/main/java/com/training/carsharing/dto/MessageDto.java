package com.training.carsharing.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@Setter
public class MessageDto extends BaseDto {

    @Size(min = 1, max = 500)
    private String message;

    @NotNull
    private Long adId;

    @NotNull
    private Long senderId;

    private String senderEmail;

    @NotNull
    private Long recipientId;

    private String recipientEmail;

    @NotNull
    private boolean viewed;
}
