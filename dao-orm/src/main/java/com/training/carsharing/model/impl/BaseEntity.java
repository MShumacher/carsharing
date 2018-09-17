package com.training.carsharing.model.impl;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.jpa.domain.AbstractAuditable;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;

@MappedSuperclass
@EntityListeners({AuditingEntityListener.class})
@JsonIgnoreProperties(value = {"hibernateLazyInitializer", "handler", "createdDate", "lastModifiedDate", "createdBy", "lastModifiedBy", "new", "version"})
//@JsonIgnoreProperties(value = {"createdAt", "updatedAt"}, allowGetters = true)
@Getter
@Setter
public abstract class BaseEntity extends AbstractAuditable<UserAccount, Long> {

    public static int DEFAULT_VERSION = 1;

    @Column
    @Version
    private Integer version;

    @Override
    public void setId(Long id) {
        super.setId(id);
    }

    @Override
    public String toString() {
        return "id=" + getId() + ", version=" + version;
    }
}
