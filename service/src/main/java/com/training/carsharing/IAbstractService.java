package com.training.carsharing;

import javax.transaction.Transactional;
import java.lang.reflect.InvocationTargetException;
import java.util.List;

public interface IAbstractService <T, DAO, ID> {

    T createEntity() throws InvocationTargetException, IllegalAccessException, NoSuchMethodException;

    T select(ID id) throws InvocationTargetException, IllegalAccessException, NoSuchMethodException;

    T selectFullInfo(ID id) throws InvocationTargetException, IllegalAccessException, NoSuchMethodException;

    List<T> selectAll() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException;

    List<T> selectAllFullInfo() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException;

    @Transactional
    void save(T entity) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException;

    @Transactional
    void deleteAll() throws InvocationTargetException, IllegalAccessException, NoSuchMethodException;

    @Transactional
    void delete(ID id) throws InvocationTargetException, IllegalAccessException, NoSuchMethodException;
}
