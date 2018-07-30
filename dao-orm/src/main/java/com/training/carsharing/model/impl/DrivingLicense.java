package com.training.carsharing.model.impl;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Date;

@Entity
public class DrivingLicense {

    public static int DEFAULT_VERSION = 1;

    @GenericGenerator(name = "generator", strategy = "foreign", parameters = @org.hibernate.annotations.Parameter(name = "property", value = "userAccount"))
    @Id
    @GeneratedValue(generator = "generator")
    private Integer id;

    @Column
    private String number;

    @Column
    private Date expirationDate;

    @Column
    private String categories;

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
        return "DrivingLicense{ id=" + id +
                ", number='" + number + '\'' +
                ", expirationDate=" + expirationDate +
                ", categories='" + categories + '\'' +
                "}";
    }
}
