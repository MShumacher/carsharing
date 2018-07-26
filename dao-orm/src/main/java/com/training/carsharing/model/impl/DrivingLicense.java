package com.training.carsharing.model.impl;

import com.training.carsharing.model.IDrivingLicense;
import com.training.carsharing.model.IUserAccount;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Date;

@Entity
public class DrivingLicense implements IDrivingLicense {

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
    public String getNumber() {
        return number;
    }

    @Override
    public void setNumber(String number) {
        this.number = number;
    }

    @Override
    public Date getExpirationDate() {
        return expirationDate;
    }

    @Override
    public void setExpirationDate(Date expirationDate) {
        this.expirationDate = expirationDate;
    }

    @Override
    public String getCategories() {
        return categories;
    }

    @Override
    public void setCategories(String categories) {
        this.categories = categories;
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
        return "DrivingLicense{ id=" + id +
                ", number='" + number + '\'' +
                ", expirationDate=" + expirationDate +
                ", categories='" + categories + '\'' +
                "}";
    }
}
