package com.training.carsharing.repository;

import com.training.carsharing.model.impl.UserAccount;

import java.util.List;

public interface UserAccountRepository extends AbstractRepository<UserAccount, Long> {

    List<UserAccount> findByEmail(String name);
}
