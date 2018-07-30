package com.training.carsharing.model.impl;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Date;

@Entity
public class Passport {

    public static int DEFAULT_VERSION = 1;

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
    private UserAccount userAccount;

    public Integer getId() {
        return id;
    }

    public void setId(final Integer id) {
        this.id = id;
    }

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

    public UserAccount getUserAccount() {
        return userAccount;
    }

    public void setUserAccount(final UserAccount userAccount) {
        this.userAccount = userAccount;
    }

    public Integer getVersion() {
        return version;
    }

    public void setVersion(final Integer version) {
        this.version = version;
    }

    public Date getCreated() {
        return new Date(created);
    }

    public void setCreated(final Date created) {
        this.created = created.getTime();
    }

    public Date getUpdated() {
        return new Date(updated);
    }

    public void setUpdated(final Date updated) {
        this.updated = updated.getTime();
    }

    @Override
    public String toString() {
        return "Passport{id=" + id +
                ", fullName='" + fullName + '\'' +
                ", number='" + number + '\'' +
                ", issuePlace='" + issuePlace + '\'' +
                ", issueDate=" + issueDate +
                ", birthPlace='" + birthPlace + '\'' +
                ", birthday=" + birthday +
                "} ";
    }
}
