package com.training.carsharing.dao;

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
}
