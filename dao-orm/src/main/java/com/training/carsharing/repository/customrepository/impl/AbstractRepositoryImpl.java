package com.training.carsharing.repository.customrepository.impl;

import com.training.carsharing.model.impl.Ad;
import com.training.carsharing.model.impl.BaseEntity;
import com.training.carsharing.repository.customrepository.AbstractRepositoryCustom;
import org.springframework.data.repository.NoRepositoryBean;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@NoRepositoryBean
public abstract class AbstractRepositoryImpl<T, ID> implements AbstractRepositoryCustom<T, ID> {

    @PersistenceContext
    private EntityManager entityManager;

    private final Class<? extends T> entityClass;

    protected AbstractRepositoryImpl(final Class<? extends T> entityClass) {
        this.entityClass = entityClass;
    }

    protected EntityManager getEntityManager() {
        return entityManager;
    }

    @Override
    public T createEntity() {
        BaseEntity entity=null;
        try {
            entity = (BaseEntity) entityClass.newInstance();
        } catch (InstantiationException | IllegalAccessException e) {
            //TODO something with this
            e.printStackTrace();
        }
        entity.setVersion(Ad.DEFAULT_VERSION);
        return (T) entity;
    }
}
