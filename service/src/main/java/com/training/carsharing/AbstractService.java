package com.training.carsharing;

import javax.transaction.Transactional;
import java.util.List;

public interface AbstractService<T, ID> {

    T createEntity();

    T findById(ID id);

    T findOneFullInfo(ID id);

    List<T> findAll();

    List<T> findAllFullInfo();

    @Transactional
    T save(T entity);

    @Transactional
    void deleteAll();

    @Transactional
    void delete(T entity);
}
