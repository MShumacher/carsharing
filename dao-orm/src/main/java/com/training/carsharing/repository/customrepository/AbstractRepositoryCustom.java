package com.training.carsharing.repository.customrepository;

import org.springframework.data.repository.NoRepositoryBean;

import java.util.List;

@NoRepositoryBean
public interface AbstractRepositoryCustom<ENTITY, ID> {

    ENTITY createEntity();

    ENTITY findOneFullInfo(ID id);

    List<ENTITY> findAllFullInfo();
}
