package com.training.carsharing.dto;

import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;
import java.time.LocalDate;
import java.util.Date;

@Getter
@Setter
public class PassportDto extends BaseWithNameDto {

    @Size(min = 1, max = 100)
    private String fullName;

    @Size(min = 1, max = 50)
    private String number;

    @Size(min = 1, max = 100)
    private String issuePlace;

    @Past
    @DateTimeFormat(pattern = "dd.MM.yyyy")
    @NotNull
    private Date issueDate;

    @Size(min = 1, max = 500)
    private String birthPlace;

    @Past
    @DateTimeFormat(pattern = "dd.MM.yyyy")
    @NotNull
    private Date birthday;

    @NotNull
    private Long userAccountId;

    private String userAccountEmail;
}
