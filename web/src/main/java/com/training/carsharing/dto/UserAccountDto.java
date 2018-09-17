package com.training.carsharing.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@Setter
public class UserAccountDto extends BaseWithNameDto {

    @Email
    @Size(min = 1, max = 100)
    private String email;

    @Size(min = 1, max = 100)
    private String password;

    @Size(min = 1, max = 100)
    private String photoLink;

    @Size(min = 1, max = 50)
    private String phone;
    @Size(min = 1, max = 50)
    private String role;

    @Size(min = 1, max = 50)
    private String verifyKey;

    @NotNull
    private boolean verified;

    //    @NotNull
    private Long passportId;

    private String passportNumber;

    //    @NotNull
    private Long drivingLicenseId;

    private String drivingLicenseNumber;
}
