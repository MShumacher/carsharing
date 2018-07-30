package com.training.carsharing.model.impl;

import javax.persistence.*;
import java.util.Date;

@MappedSuperclass
public abstract class BaseEntity {

    public static int DEFAULT_VERSION = 1;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column
    @Version
    private Integer version;

    @Column(updatable = false)
    private Long created;

    @Column
    private Long updated;

    public Integer getId() {
        return id;
    }

    public void setId(final Integer id) {
        this.id = id;
    }

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
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
        return " id=" + id;
    }
}
