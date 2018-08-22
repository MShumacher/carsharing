package com.training.carsharing.model.impl;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDateTime;

@MappedSuperclass
@EntityListeners({AuditingEntityListener.class})
public abstract class BaseEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    public static int DEFAULT_VERSION = 1;

    @CreatedDate
    @Column
    private LocalDateTime createdDate;

//    @CreatedBy
//    @ManyToOne
//    private UserAccount createdBy;

    @LastModifiedDate
    @Column
    private LocalDateTime lastModifiedDate;

//    @LastModifiedBy
//    @ManyToOne
//    private UserAccount lastModifiedBy;

    @Column
    @Version
    private Integer version;

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }

    @Override
    public String toString() {
        return " id=" + getId();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDateTime getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(LocalDateTime createdDate) {
        this.createdDate = createdDate;
    }

    public LocalDateTime getLastModifiedDate() {
        return lastModifiedDate;
    }

    public void setLastModifiedDate(LocalDateTime lastModifiedDate) {
        this.lastModifiedDate = lastModifiedDate;
    }

//    public UserAccount getCreatedBy() {
//        return createdBy;
//    }
//
//    public void setCreatedBy(UserAccount createdBy) {
//        this.createdBy = createdBy;
//    }
//
//    public UserAccount getLastModifiedBy() {
//        return lastModifiedBy;
//    }
//
//    public void setLastModifiedBy(UserAccount lastModifiedBy) {
//        this.lastModifiedBy = lastModifiedBy;
//    }
}
