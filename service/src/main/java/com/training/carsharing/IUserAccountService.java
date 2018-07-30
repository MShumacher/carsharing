package com.training.carsharing;

import com.training.carsharing.dao.IUserAccountDao;
import com.training.carsharing.model.impl.UserAccount;

public interface IUserAccountService extends IAbstractService<UserAccount, IUserAccountDao, Integer> {

  }

