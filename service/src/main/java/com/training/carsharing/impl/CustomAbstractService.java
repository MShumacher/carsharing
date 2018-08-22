package com.training.carsharing.impl;

import com.training.carsharing.AbstractService;
import com.training.carsharing.repository.AbstractRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;

public abstract class CustomAbstractService<ENTITY, ID> implements AbstractService<ENTITY, ID> {

    public CustomAbstractService(Class<? extends ENTITY> entityClass) {
        this.entityClass = entityClass;
    }

    protected static final Logger LOGGER = LoggerFactory.getLogger(CustomAbstractService.class);

    private Class<? extends ENTITY> entityClass;

    @Autowired
    private AbstractRepository<ENTITY, ID> repository;

    @Override
    public ENTITY createEntity() {
        ENTITY entity = repository.createEntity();
        return entity;
    }

    @Override
    public ENTITY save(ENTITY entity) {
        LOGGER.info("saved entity: {}", entity);
        return repository.save(entity);
    }

    @Override
    public ENTITY findById(final ID id) {
        final Optional<ENTITY> entityById = repository.findById(id);
        LOGGER.info("entityById[{}]: {}", id, entityById);
        return entityById.isPresent() ? entityById.get() : null;
    }

    @Override
    public ENTITY findOneFullInfo(final ID id) {
        final ENTITY entity = repository.findOneFullInfo(id);
        LOGGER.debug("entityById[{}]: {}", id, entity);
        return entity;
    }

    @Override
    public List<ENTITY> findAll() {
        List<ENTITY> all = (List<ENTITY>) repository.findAll();
        LOGGER.debug("total count {} entities in DB: {}", repository.getClass().getSimpleName(), all.size());
        return all;
    }

    @Override
    public List<ENTITY> findAllFullInfo() {
        List<ENTITY> all = repository.findAllFullInfo();
        LOGGER.debug("total count {} entities in DB: {}", repository.getClass().getSimpleName(), all.size());
        return all;
    }

    @Override
    public void delete(final ENTITY entity) {
        LOGGER.info("delete {} entity: {}", repository.getClass().getSimpleName(), entity);
        repository.delete(entity);
    }

    @Override
    public void deleteAll() {
        LOGGER.info("delete all {} entities", repository.getClass().getSimpleName());
        repository.deleteAll();
    }
}
