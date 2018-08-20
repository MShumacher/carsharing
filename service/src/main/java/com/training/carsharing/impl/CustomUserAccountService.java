package com.training.carsharing.impl;

import com.training.carsharing.UserAccountService;
import com.training.carsharing.model.impl.UserAccount;
import com.training.carsharing.repository.UserAccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CustomUserAccountService extends CustomAbstractService<UserAccount, Long> implements UserAccountService {

    public CustomUserAccountService() {
        super(UserAccount.class);
    }

    @Autowired
    private UserAccountRepository userAccountRepository;

    @Override
    public UserAccount getCurrentUserAccount() {
        //TODO get current user
        return userAccountRepository.findById(1L).get();
    }
}
