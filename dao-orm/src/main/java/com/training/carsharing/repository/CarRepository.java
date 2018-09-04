package com.training.carsharing.repository;

import com.training.carsharing.model.impl.Car;
import com.training.carsharing.repository.customrepository.CarRepositoryCustom;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CarRepository extends AbstractRepository<Car, Long>, CarRepositoryCustom {

    @Query("SELECT c FROM Car c WHERE c.id NOT IN (SELECT a.car.id FROM Ad a WHERE a.car <> null) ORDER BY c.plate")
    List<Car> findWithoutAd();

    @Query("SELECT c FROM Car c WHERE c.id = (SELECT a.car.id FROM Ad a WHERE a.id = :adId)")
    Car findByAdId(@Param("adId") Long adId);
}
