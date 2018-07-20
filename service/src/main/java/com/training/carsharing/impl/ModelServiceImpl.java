package com.training.carsharing.impl;

import com.training.carsharing.IModelService;
import com.training.carsharing.dao.IModel;
import com.training.carsharing.orm.IModelDao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class ModelServiceImpl implements IModelService {

    private static final Logger LOGGER = LoggerFactory.getLogger(ModelServiceImpl.class);

    @Autowired
    private IModelDao dao;

    @Override
    public IModel createEntity() {
        return dao.createEntity();
    }

    @Override
    public void save(final IModel entity) {
        final Date modifiedOn = new Date();
        entity.setUpdated(modifiedOn);
        if (entity.getId() == null) {
            entity.setCreated(modifiedOn);
            dao.insert(entity);
            LOGGER.info("new saved Model: {}", entity);
        } else {
            dao.update(entity);
        }
    }

    @Override
    public IModel get(final Integer id) {
        final IModel entity = dao.get(id);
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
        LOGGER.info("delete all Model entities");
        dao.deleteAll();
    }

    @Override
    public List<IModel> selectAll() {
        final List<IModel> all = dao.selectAll();
        LOGGER.debug("total count in DB: {}", all.size());
        return all;
    }

    @Override
    public List<IModel> selectAllFullInfo() {
        final List<IModel> all = dao.selectAllFullInfo();
        LOGGER.debug("total count in DB: {}", all.size());
        return all;
    }

//        @Override
//        public List<IModel> find(final ModelFilter filter) {
//            return dao.find(filter);
//        }
//
//        @Override
//        public long getCount(final ModelFilter filter) {
//            return dao.getCount(filter);
//        }

}
