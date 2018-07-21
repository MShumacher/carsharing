package com.training.carsharing.model;

import java.util.Date;

public interface IBaseEntity {

    int DEFAULT_VERSION = 1;

    Integer getId();

    void setId(Integer id);

    Integer getVersion();

    void setVersion(Integer version);

    Date getCreated();

    void setCreated(Date created);

    Date getUpdated();

    void setUpdated(Date updated);
}
