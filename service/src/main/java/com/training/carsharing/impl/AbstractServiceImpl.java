package com.training.carsharing.impl;

import com.training.carsharing.IAbstractService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Date;
import java.util.List;

public class AbstractServiceImpl<T, DAO, ID> implements IAbstractService<T, DAO, ID> {

    public AbstractServiceImpl(Class<? extends T> entityClass) {
        this.entityClass = entityClass;
    }

    private static final Logger LOGGER = LoggerFactory.getLogger(AbstractServiceImpl.class);

    private Class<? extends T> entityClass;

    @Autowired
    private DAO dao;

    @Override
    public T createEntity() throws InvocationTargetException, IllegalAccessException, NoSuchMethodException {
        Method method = dao.getClass().getMethod("createEntity");
        T entity = (T) method.invoke(dao);
        return entity;
    }

    @Override
    public void save(final T entity) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        Method methodSetUpdated=entityClass.getMethod("setUpdated", Date.class);
        final Date modifiedOn = new Date();
        methodSetUpdated.invoke(entity, modifiedOn);
        Method methodGetId=entityClass.getMethod("getId");
        if (methodGetId.invoke(entity) == null) {
            Method methodSetCreated=entityClass.getMethod("setCreated", Date.class);
            methodSetCreated.invoke(entity, modifiedOn);
            LOGGER.info("new saved entity: {}", entity);
            Method method = dao.getClass().getMethod("insert", Object.class/*entityClass*/);
            method.invoke(dao,entity);
        } else {
            LOGGER.info("updated entity: {}", entity);
            Method method = dao.getClass().getMethod("update", Object.class);
            method.invoke(dao,entity);
        }
    }

    @Override
    public T select(final ID id) throws InvocationTargetException, IllegalAccessException, NoSuchMethodException {
        Method method = dao.getClass().getMethod("select", Object.class/*id.getClass()*/);
        final T entity = (T) method.invoke(dao,id);
        LOGGER.debug("entityById[{}]: {}", id, entity);
        return entity;
    }

    @Override
    public T selectFullInfo(final ID id) throws InvocationTargetException, IllegalAccessException, NoSuchMethodException {
        Method method = dao.getClass().getMethod("selectFullInfo", Object.class);
        final T entity = (T) method.invoke(dao,id);
        LOGGER.debug("entityById[{}]: {}", id, entity);
        return entity;
    }

    @Override
    public List<T> selectAll() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        Method method = dao.getClass().getMethod("selectAll");
        List<T> all = (List<T>) method.invoke(dao);
        LOGGER.debug("total count {} entities in DB: {}", dao.getClass().getSimpleName(), all.size());
        return all;
    }

    @Override
    public List<T> selectAllFullInfo() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        Method method = dao.getClass().getMethod("selectAllFullInfo");
        List<T> all = (List<T>) method.invoke(dao);
        LOGGER.debug("total count {} entities in DB: {}", dao.getClass().getSimpleName(), all.size());
        return all;
    }

    @Override
    public void delete(final ID id) throws InvocationTargetException, IllegalAccessException, NoSuchMethodException {
        LOGGER.info("delete {} entity: {}", dao.getClass().getSimpleName(), id);
        Method method = dao.getClass().getMethod("delete", Object.class);
        method.invoke(dao, id);
    }

    @Override
    public void deleteAll() throws InvocationTargetException, IllegalAccessException, NoSuchMethodException {
        LOGGER.info("delete all {} entities", dao.getClass().getSimpleName());
        Method method = dao.getClass().getMethod("deleteAll");
        method.invoke(dao);
        }
}
