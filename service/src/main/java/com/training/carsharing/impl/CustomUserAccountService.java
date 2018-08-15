package com.training.carsharing.impl;

import com.training.carsharing.UserAccountService;
import com.training.carsharing.model.impl.UserAccount;
import com.training.carsharing.repository.UserAccountRepository;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Date;

@Service
public class CustomUserAccountService extends CustomAbstractService<UserAccount, UserAccountRepository, Integer> implements UserAccountService {

    public CustomUserAccountService() {
        super(UserAccount.class);
    }
}
