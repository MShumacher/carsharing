package com.training.carsharing.converter;

import com.training.carsharing.AbstractService;
import com.training.carsharing.model.impl.BaseEntity;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.PersistenceException;
import java.util.function.Function;

public abstract class AbstractFromDtoConverter<DTO, ENTITY> implements Function<DTO, ENTITY> {

    @Autowired
    private AbstractService<ENTITY, Long> service;

    protected ENTITY applyBaseEntity(Long id) throws PersistenceException {
        BaseEntity entity = (BaseEntity) service.createEntity();
        if (id != null) {
            BaseEntity entityFromDb = (BaseEntity) service.findById(id);
            if (entityFromDb == null) {
                // случай, когда кто-то удалил сущность, которую мы пытаемся перезаписать
                throw new PersistenceException();
            } else {
                entity.setId(entityFromDb.getId());
                entity.setCreatedDate(entityFromDb.getCreatedDate().get());
                entity.setCreatedBy(entityFromDb.getCreatedBy().get());
                entity.setLastModifiedDate(entityFromDb.getLastModifiedDate().get());
                entity.setLastModifiedBy(entityFromDb.getLastModifiedBy().get());
            }
        }
        return(ENTITY) entity;
    }
}
