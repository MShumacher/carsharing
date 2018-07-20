package com.training.carsharing.dao;

import java.util.Date;

public interface IBaseEntity {

    Integer getId();

    void setId(Integer id);

    Integer getVersion();

    void setVersion(Integer version);

    Date getCreated();

    void setCreated(Date created);

    Date getUpdated();

    void setUpdated(Date updated);
}
