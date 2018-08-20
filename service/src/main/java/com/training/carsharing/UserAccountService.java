package com.training.carsharing;

import com.training.carsharing.model.impl.UserAccount;

public interface UserAccountService extends AbstractService<UserAccount, Long> {

    UserAccount getCurrentUserAccount();
}

