package com.training.carsharing;

import com.training.carsharing.model.impl.UserAccount;

import java.util.List;

public interface UserAccountService extends AbstractService<UserAccount, Long> {

    UserAccount getCurrentUserAccount();

    UserAccount findByEmail(String email);

    boolean isPasswordCorrect(final UserAccount userAccount, final String password);

    List<UserAccount> findWithoutPassport();
    
    List<UserAccount> findWithoutDrivingLicense();

    UserAccount findByDrivingLicense(Long id);

    UserAccount findByPassport(Long id);
}

