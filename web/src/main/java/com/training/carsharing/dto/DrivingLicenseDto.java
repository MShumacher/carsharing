package com.training.carsharing.dto;

import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.Valid;
import javax.validation.constraints.Future;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDate;
import java.util.Date;

@Getter
@Setter
public class DrivingLicenseDto extends BaseWithNameDto {

    @Size(min = 1, max = 50)
    private String number;

    @Future
    @DateTimeFormat(pattern = "dd.MM.yyyy")
    @NotNull
    private Date expirationDate;

    @Size(min = 1, max = 50)
    private String categories;

    @NotNull
    private Long userAccountId;

    private String userAccountEmail;
}
