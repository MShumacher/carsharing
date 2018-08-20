package com.training.carsharing.impl;

import com.training.carsharing.UserAccountService;
import com.training.carsharing.model.impl.UserAccount;
import com.training.carsharing.repository.UserAccountRepository;
import org.springframework.stereotype.Service;

@Service
public class CustomUserAccountService extends CustomAbstractService<UserAccount, Long> implements UserAccountService {

    public CustomUserAccountService() {
        super(UserAccount.class);
    }
}
