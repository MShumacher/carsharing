package com.training.carsharing.impl;

import com.training.carsharing.IAdService;
import com.training.carsharing.dao.IAdDao;
import com.training.carsharing.dao.ICarDao;
import com.training.carsharing.model.IAd;
import com.training.carsharing.model.ICar;
import com.training.carsharing.model.impl.Ad;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.PersistenceException;
import java.util.Date;
import java.util.List;

@Service
public class AdServiceImpl extends AbstractServiceImpl<IAd, IAdDao, Integer> implements IAdService {

    private static final Logger LOGGER = LoggerFactory.getLogger(AbstractServiceImpl.class);

    @Autowired
    private IAdDao adDao;

    @Autowired
    private ICarDao carDao;

    public AdServiceImpl() {
        super(Ad.class);
    }

//    @Override
//    public void save(final IAd ad, final ICar car) throws PersistenceException {
//        final Date modifiedOn = new Date();
//        ad.setUpdated(modifiedOn);
//        car.setUpdated(modifiedOn);
//        try {
//            if (car.getId() == null) {
//                car.setCreated(modifiedOn);
//                LOGGER.info("new saved entity: {}", car);
//                carDao.insert(car);
//            } else {
//                LOGGER.info("updated entity: {}", car);
//                carDao.update(car);
//            }
//            if (ad.getId() == null) {
//                ad.setCreated(modifiedOn);
//                ad.setCar(car);
//                ad.setId(car.getId());
//                LOGGER.info("new saved entity: {}", ad);
//                adDao.insert(ad);
//            } else {
//                LOGGER.info("updated entity: {}", ad);
//                adDao.update(ad);
//            }
//        } catch (PersistenceException e) {
//            LOGGER.warn(e.getMessage());
//            throw e;
//        }
//    }
}
