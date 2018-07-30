package com.training.carsharing;

import com.training.carsharing.dao.ICarDao;
import com.training.carsharing.model.impl.Car;

public interface ICarService extends IAbstractService<Car, ICarDao, Integer> {

}
