package com.training.carsharing.model.impl;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.springframework.data.jpa.domain.AbstractAuditable;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;

@MappedSuperclass
@EntityListeners({AuditingEntityListener.class})
@JsonIgnoreProperties(value = {"hibernateLazyInitializer", "handler", "createdDate", "lastModifiedDate", "createdBy", "lastModifiedBy", "new", "version"})
//@JsonIgnoreProperties(value = {"createdAt", "updatedAt"}, allowGetters = true)
public abstract class BaseEntity extends AbstractAuditable<UserAccount, Long> {

    public static int DEFAULT_VERSION = 1;

    @Column
    @Version
    private Integer version;

//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Long id;

    @Override
    public void setId(Long id) {
        super.setId(id);
    }

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }

    @Override
    public String toString() {
        return "id=" + getId() + ", version=" + getVersion();
    }
}
