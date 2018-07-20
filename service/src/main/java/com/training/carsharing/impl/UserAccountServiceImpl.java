package com.training.carsharing.impl;

import com.training.carsharing.IUserAccountService;
import com.training.carsharing.dao.IUserAccount;
import com.training.carsharing.orm.IUserAccountDao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class UserAccountServiceImpl implements IUserAccountService {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserAccountServiceImpl.class);

    @Autowired
    private IUserAccountDao dao;

    @Override
    public IUserAccount createEntity() {
        return dao.createEntity();
    }

    @Override
    public void save(final IUserAccount entity) {
        final Date modifiedOn = new Date();
        entity.setUpdated(modifiedOn);
        if (entity.getId() == null) {
            entity.setCreated(modifiedOn);
            dao.insert(entity);
            LOGGER.info("new saved UserAccount: {}", entity);
        } else {
            dao.update(entity);
        }
    }

    @Override
    public IUserAccount get(final Integer id) {
        final IUserAccount entity = dao.get(id);
        LOGGER.debug("entityById: {}", entity);
        return entity;
    }

    @Override
    public void delete(final Integer id) {
        LOGGER.info("delete entity: {}", id);
        dao.delete(id);
    }

    @Override
    public void deleteAll() {
        LOGGER.info("delete all UserAccount entities");
        dao.deleteAll();
    }

    @Override
    public List<IUserAccount> selectAll() {
        final List<IUserAccount> all = dao.selectAll();
        LOGGER.debug("total count in DB: {}", all.size());
        return all;
    }

    @Override
    public List<IUserAccount> selectAllFullInfo() {
        final List<IUserAccount> all = dao.selectAllFullInfo();
        LOGGER.debug("total count in DB: {}", all.size());
        return all;
    }

//        @Override
//        public List<IUserAccount> find(final UserAccountFilter filter) {
//            return dao.find(filter);
//        }
//
//        @Override
//        public long getCount(final UserAccountFilter filter) {
//            return dao.getCount(filter);
//        }

}
