package com.training.carsharing.dto;

import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;
import java.time.LocalDate;
import java.util.Date;

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

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getIssuePlace() {
        return issuePlace;
    }

    public void setIssuePlace(String issuePlace) {
        this.issuePlace = issuePlace;
    }

    public Date getIssueDate() {
        return issueDate;
    }

    public void setIssueDate(Date issueDate) {
        this.issueDate = issueDate;
    }

    public String getBirthPlace() {
        return birthPlace;
    }

    public void setBirthPlace(String birthPlace) {
        this.birthPlace = birthPlace;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
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
