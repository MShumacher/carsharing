package com.training.carsharing.impl;

import com.training.carsharing.AbstractService;
import com.training.carsharing.repository.AbstractRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.orm.ObjectOptimisticLockingFailureException;

import javax.persistence.PersistenceException;
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
    public ENTITY save(ENTITY entity) throws ObjectOptimisticLockingFailureException, PersistenceException {
        try {
            LOGGER.info("saved entity: {}", entity);
            return repository.save(entity);
        } catch (ObjectOptimisticLockingFailureException | PersistenceException e) {
            LOGGER.warn(e.getClass().getSimpleName()
                    + "Row was updated/deleted by another transaction (or unsaved-value mapping was incorrect)");
            throw e;
        }
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
        LOGGER.debug("total count {} entities in DB: {}", entityClass.getSimpleName(), all.size());
        return all;
    }

    @Override
    public List<ENTITY> findAllFullInfo() {
        List<ENTITY> all = repository.findAllFullInfo();
        LOGGER.debug("total count {} entities in DB: {}", entityClass.getSimpleName(), all.size());
        return all;
    }

    @Override
    public void delete(final ENTITY entity) {
        LOGGER.info("delete {} entity: {}", entityClass.getSimpleName(), entity);
        repository.delete(entity);
    }

    @Override
    public void deleteAll() {
        LOGGER.info("delete all {} entities", entityClass.getSimpleName());
        repository.deleteAll();
    }

    @Override
    public long count() {
        final long count = repository.count();
        LOGGER.info("total count {} entities in DB: {}", entityClass.getSimpleName(), count);
        return count;
    }

    @Override
    public List<ENTITY> findAll(int page, int size, String sortBy, boolean isAscending) {
        if (sortBy == null) {
            return repository.findAll(PageRequest.of(page - 1, size)).getContent();
        }
        if (isAscending) {
            return repository.findAll(PageRequest.of(page - 1, size, Sort.by(sortBy).ascending())).getContent();
        } else {
            return repository.findAll(PageRequest.of(page - 1, size, Sort.by(sortBy).descending())).getContent();
        }
//        Pageable pageable = PageRequest.of(page - 1,size, Sort.Direction.ASC,"id");
    }

    @Override
    public List<ENTITY> findAll(int page, int size) {
        return findAll(page, size, null, true);
    }
}
