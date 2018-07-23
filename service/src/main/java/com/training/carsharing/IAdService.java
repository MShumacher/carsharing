package com.training.carsharing;

import com.training.carsharing.dao.IAdDao;
import com.training.carsharing.model.IAd;
import com.training.carsharing.model.ICar;

import javax.persistence.PersistenceException;
import javax.transaction.Transactional;
import java.util.List;

public interface IAdService extends IAbstractService<IAd, IAdDao, Integer> {

//    @Transactional
//    void save(IAd ad, ICar car) throws PersistenceException;
}
