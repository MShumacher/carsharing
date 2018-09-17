package com.training.carsharing.dto;

import com.training.carsharing.model.impl.UserAccount;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class BaseDto {

    private Long id;
    private int version;
    private LocalDateTime createdDate;
    private LocalDateTime lastModifiedDate;
    private UserAccount createdBy;
    private UserAccount lastModifiedBy;
}
