package com.training.carsharing;

import com.training.carsharing.dao.IUserAccount;

import javax.transaction.Transactional;
import java.util.List;

public interface IUserAccountService {

        IUserAccount createEntity();

        IUserAccount get(Integer id);

        List<IUserAccount> selectAll();

        List<IUserAccount> selectAllFullInfo();

        @Transactional
        void save(IUserAccount entity);

        @Transactional
        void delete(Integer id);

        @Transactional
        void deleteAll();



//        List<IUserAccount> find(UserAccountFilter filter);
//
//        long getCount(UserAccountFilter filter);

    }

