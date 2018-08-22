package com.training.carsharing.repository;

import com.training.carsharing.model.impl.UserAccount;
import com.training.carsharing.repository.customrepository.UserAccountRepositoryCustom;

import java.util.List;

public interface UserAccountRepository extends AbstractRepository<UserAccount, Long>, UserAccountRepositoryCustom {

    List<UserAccount> findByEmail(String name);
}
