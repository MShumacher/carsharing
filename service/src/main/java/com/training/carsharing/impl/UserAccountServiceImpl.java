package com.training.carsharing.impl;

import com.training.carsharing.IUserAccountService;
import com.training.carsharing.dao.IUserAccountDao;
import com.training.carsharing.model.IUserAccount;
import com.training.carsharing.model.impl.UserAccount;
import org.springframework.stereotype.Service;

@Service
public class UserAccountServiceImpl extends AbstractServiceImpl<IUserAccount, IUserAccountDao, Integer> implements IUserAccountService {

    public UserAccountServiceImpl() {
        super(UserAccount.class);
    }



}
