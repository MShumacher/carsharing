package com.training.carsharing.impl;

import com.training.carsharing.AbstractService;
import com.training.carsharing.model.impl.CarParameter;
import com.training.carsharing.repository.CarParameterRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Date;
import java.util.List;
import java.util.Optional;

public class CustomAbstractService<T, REPOSITORY, ID> implements AbstractService<T, ID> {

    public CustomAbstractService(Class<? extends T> entityClass) {
        this.entityClass = entityClass;
    }

    protected static final Logger LOGGER = LoggerFactory.getLogger(CustomAbstractService.class);

    private Class<? extends T> entityClass;

    @Autowired
    private REPOSITORY repository;
    @Autowired
    private CarParameterRepository carParameterRepository;

    @Override
    public T createEntity() throws InvocationTargetException, IllegalAccessException, NoSuchMethodException {
        Method method = repository.getClass().getMethod("createEntity");
        T entity = (T) method.invoke(repository);
        return entity;
    }

    @Override
    public T save(T entity) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
//        Method[] methods = repository.getClass().getMethods();
//        for (Method method : methods) {
//            System.out.println(method.getName());
//        }
        Method methodSetUpdated = entityClass.getMethod("setUpdated", Date.class);
        final Date modifiedOn = new Date();
        methodSetUpdated.invoke(entity, modifiedOn);
        Method methodGetId = entityClass.getMethod("getId");
        if (methodGetId.invoke(entity) == null) {
            Method methodSetCreated = entityClass.getMethod("setCreated", Date.class);
            methodSetCreated.invoke(entity, modifiedOn);
            LOGGER.info("new saved entity: {}", entity);
        } else {
            LOGGER.info("updated entity: {}", entity);
        }
//        CarParameter carParameter = carParameterRepository.save((CarParameter) entity);
        Method method = repository.getClass().getMethod("save", Object.class);
        T entity2 = (T) method.invoke(repository, entity);
        return entity2;
    }

    @Override
    public T findById(final ID id) throws InvocationTargetException, IllegalAccessException, NoSuchMethodException {
        Method method = repository.getClass().getMethod("findById", Object.class/*id.getClass()*/);
        //findById(ID id) возвращает тип Optional<T>. чтобы получить entity, нужно сделать get
        final Optional entityOptional = (Optional) method.invoke(repository, id);
        T entity = null;
        if (entityOptional.isPresent()) {
            entity = (T) entityOptional.get();
        }
        LOGGER.debug("entityById[{}]: {}", id, entity);
        return entity;
    }

    @Override
    public T findOneFullInfo(final ID id) throws InvocationTargetException, IllegalAccessException, NoSuchMethodException {
        Method method = repository.getClass().getMethod("findOneFullInfo", Object.class);
        final T entity = (T) method.invoke(repository, id);
        LOGGER.debug("entityById[{}]: {}", id, entity);
        return entity;
    }

    @Override
    public List<T> findAll() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        Method method = repository.getClass().getMethod("findAll");
        List<T> all = (List<T>) method.invoke(repository);
        LOGGER.debug("total count {} entities in DB: {}", repository.getClass().getSimpleName(), all.size());
        return all;
    }

    @Override
    public List<T> findAllFullInfo() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        Method method = repository.getClass().getMethod("findAllFullInfo");
        List<T> all = (List<T>) method.invoke(repository);
        LOGGER.debug("total count {} entities in DB: {}", repository.getClass().getSimpleName(), all.size());
        return all;
    }

    @Override
    public void delete(final T entity) throws InvocationTargetException, IllegalAccessException, NoSuchMethodException {
        LOGGER.info("delete {} entity: {}", repository.getClass().getSimpleName(), entity);
        Method method = repository.getClass().getMethod("delete", Object.class);
        method.invoke(repository, entity);
    }

    @Override
    public void deleteAll() throws InvocationTargetException, IllegalAccessException, NoSuchMethodException {
        LOGGER.info("delete all {} entities", repository.getClass().getSimpleName());
        Method method = repository.getClass().getMethod("deleteAll");
        method.invoke(repository);
    }
}
