package com.training.carsharing.repository;

import com.training.carsharing.repository.customrepository.AbstractRepositoryCustom;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.data.repository.PagingAndSortingRepository;

@NoRepositoryBean
public interface AbstractRepository<ENTITY, ID> extends PagingAndSortingRepository<ENTITY, ID>, AbstractRepositoryCustom<ENTITY,ID> {

}
