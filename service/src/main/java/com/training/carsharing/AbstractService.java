package com.training.carsharing;

import javax.transaction.Transactional;
import java.util.List;

public interface AbstractService<ENTITY, ID> {

    ENTITY createEntity();

    ENTITY findById(ID id);

    ENTITY findOneFullInfo(ID id);

    List<ENTITY> findAll();

    List<ENTITY> findAllFullInfo();

    @Transactional
    ENTITY save(ENTITY entity);

    @Transactional
    void deleteAll();

    @Transactional
    void delete(ENTITY entity);

    long count();

    List<ENTITY> findAll(int page, int size, String sortBy, boolean isAscending);

    List<ENTITY> findAll(int page, int size);
}
