package com.training.carsharing;

import com.training.carsharing.model.impl.UserAccount;

public interface UserAccountService extends AbstractService<UserAccount, Long> {

    UserAccount getCurrentUserAccount();

    UserAccount getByEmail(String email);

    boolean isPasswordCorrect(final UserAccount userAccount, final String password);
}

