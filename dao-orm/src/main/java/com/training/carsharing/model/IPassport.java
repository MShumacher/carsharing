package com.training.carsharing.model;

import java.sql.Timestamp;
import java.util.Date;

public interface IPassport extends IBaseEntity {

    String getFullName();

    void setFullName(String fullName);

    String getNumber();

    void setNumber(String number);

    String getIssuePlace();

    void setIssuePlace(String issuePlace);

    Date getIssueDate();

    void setIssueDate(Date issueDate);

    String getBirthPlace();

    void setBirthPlace(String birthPlace);

    Date getBirthday();

    void setBirthday(Date birthday);

    IUserAccount getUserAccount();

    void setUserAccount(IUserAccount userAccount);
}
