package com.training.carsharing.dto;

import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.Valid;
import javax.validation.constraints.Future;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDate;
import java.util.Date;

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

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public Date getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(Date expirationDate) {
        this.expirationDate = expirationDate;
    }

    public String getCategories() {
        return categories;
    }

    public void setCategories(String categories) {
        this.categories = categories;
    }

    public Long getUserAccountId() {
        return userAccountId;
    }

    public void setUserAccountId(Long userAccountId) {
        this.userAccountId = userAccountId;
    }

    public String getUserAccountEmail() {
        return userAccountEmail;
    }

    public void setUserAccountEmail(String userAccountEmail) {
        this.userAccountEmail = userAccountEmail;
    }
}
