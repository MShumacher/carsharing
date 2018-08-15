package com.training.carsharing;

import javax.transaction.Transactional;
import java.lang.reflect.InvocationTargetException;
import java.util.List;

public interface AbstractService<T, REPOSITORY, ID> {

    T createEntity() throws InvocationTargetException, IllegalAccessException, NoSuchMethodException;

    T findById(ID id) throws InvocationTargetException, IllegalAccessException, NoSuchMethodException;

    T findOneFullInfo(ID id) throws InvocationTargetException, IllegalAccessException, NoSuchMethodException;

    List<T> findAll() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException;

    List<T> findAllFullInfo() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException;

    @Transactional
    T save(T entity) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException;

    @Transactional
    void deleteAll() throws InvocationTargetException, IllegalAccessException, NoSuchMethodException;

    @Transactional
    void delete(T entity) throws InvocationTargetException, IllegalAccessException, NoSuchMethodException;
}
