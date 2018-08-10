package com.training.carsharing.dao;

import java.util.List;

public interface  IAbstractDao<ENTITY, ID> {

    ENTITY createEntity();

    ENTITY select(ID id);

    ENTITY selectFullInfo(ID id);

    List<ENTITY> selectAll();

    List<ENTITY> selectAllFullInfo();

    void insert(ENTITY entity);

    void update(ENTITY entity);

    void delete(ID id);

    void deleteAll();

}
