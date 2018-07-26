package com.training.carsharing.model.impl;

import com.training.carsharing.model.ICar;
import com.training.carsharing.model.IPassport;
import com.training.carsharing.model.IUserAccount;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Date;

@Entity
public class Passport implements IPassport {

    @GenericGenerator(name = "generator", strategy = "foreign", parameters = @org.hibernate.annotations.Parameter(name = "property", value = "userAccount"))
    @Id
    @GeneratedValue(generator = "generator")
    private Integer id;

    @Column
    private String fullName;

    @Column
    private String number;

    @Column
    private String issuePlace;

    @Column
    private Date issueDate;

    @Column
    private String birthPlace;

    @Column
    private Date birthday;

    @Column
    @Version
    private Integer version;

    @Column(updatable = false)
    private Long created;

    @Column
    private Long updated;

    @OneToOne(fetch = FetchType.LAZY, optional = false, targetEntity = UserAccount.class)
    @PrimaryKeyJoinColumn
    private IUserAccount userAccount;

    @Override
    public Integer getId() {
        return id;
    }

    @Override
    public void setId(final Integer id) {
        this.id = id;
    }

    @Override
    public String getFullName() {
        return fullName;
    }

    @Override
    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    @Override
    public String getNumber() {
        return number;
    }

    @Override
    public void setNumber(String number) {
        this.number = number;
    }

    @Override
    public String getIssuePlace() {
        return issuePlace;
    }

    @Override
    public void setIssuePlace(String issuePlace) {
        this.issuePlace = issuePlace;
    }

    @Override
    public Date getIssueDate() {
        return issueDate;
    }

    @Override
    public void setIssueDate(Date issueDate) {
        this.issueDate = issueDate;
    }

    @Override
    public String getBirthPlace() {
        return birthPlace;
    }

    @Override
    public void setBirthPlace(String birthPlace) {
        this.birthPlace = birthPlace;
    }

    @Override
    public Date getBirthday() {
        return birthday;
    }

    @Override
    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    @Override
    public IUserAccount getUserAccount() {
        return userAccount;
    }

    @Override
    public void setUserAccount(final IUserAccount userAccount) {
        this.userAccount = userAccount;
    }

    @Override
    public Integer getVersion() {
        return version;
    }

    @Override
    public void setVersion(final Integer version) {
        this.version = version;
    }

    @Override
    public Date getCreated() {
        return new Date(created);
    }

    @Override
    public void setCreated(final Date created) {
        this.created = created.getTime();
    }

    @Override
    public Date getUpdated() {
        return new Date(updated);
    }

    @Override
    public void setUpdated(final Date updated) {
        this.updated = updated.getTime();
    }

    @Override
    public String toString() {
        return "Passport{id=" + id+
                ", fullName='" + fullName + '\'' +
                ", number='" + number + '\'' +
                ", issuePlace='" + issuePlace + '\'' +
                ", issueDate=" + issueDate +
                ", birthPlace='" + birthPlace + '\'' +
                ", birthday=" + birthday +
                "} ";
    }
}
