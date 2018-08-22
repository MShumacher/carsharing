package com.training.carsharing.repository.customrepository.impl;

import com.training.carsharing.model.impl.Car;
import com.training.carsharing.model.impl.Car_;
import com.training.carsharing.repository.customrepository.CarRepositoryCustom;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Root;
import java.util.List;


@Repository
public class CarRepositoryImpl extends AbstractRepositoryImpl<Car, Long> implements CarRepositoryCustom {

    protected CarRepositoryImpl() {
        super(Car.class);
    }

    @Override
    public Car findOneFullInfo(final Long id) {
        final EntityManager em = getEntityManager();
        final CriteriaBuilder cb = em.getCriteriaBuilder();
        final CriteriaQuery<Car> cq = cb.createQuery(Car.class);
        final Root<Car> from = cq.from(Car.class);
        cq.select(from);

        from.fetch(Car_.ad, JoinType.LEFT);
        from.fetch(Car_.model, JoinType.LEFT);
        from.fetch(Car_.gearbox, JoinType.LEFT);
        from.fetch(Car_.bodyType, JoinType.LEFT);
        from.fetch(Car_.drive, JoinType.LEFT);
        from.fetch(Car_.engineType, JoinType.LEFT);



//        from.fetch(Car_.parameters, JoinType.LEFT);



        cq.where(cb.equal(from.get(Car_.id), id));

        final List<Car> resultList = em.createQuery(cq).getResultList();
        return resultList.isEmpty() ? null : resultList.get(0);
    }

    @Override
    public List<Car> findAllFullInfo() {
        final EntityManager em = getEntityManager();
        final CriteriaBuilder cb = em.getCriteriaBuilder();
        final CriteriaQuery<Car> cq = cb.createQuery(Car.class);
        final Root<Car> from = cq.from(Car.class);
        cq.select(from);

        from.fetch(Car_.ad, JoinType.LEFT);
        from.fetch(Car_.model, JoinType.LEFT);
        from.fetch(Car_.gearbox, JoinType.LEFT);
        from.fetch(Car_.bodyType, JoinType.LEFT);
        from.fetch(Car_.drive, JoinType.LEFT);
        from.fetch(Car_.engineType, JoinType.LEFT);
//        from.fetch(Car_.parameters, JoinType.LEFT);

        final TypedQuery<Car> q = em.createQuery(cq);
        List<Car> resultList = q.getResultList();
//        Set<Car> targetSet = new HashSet<Car>(resultList);
//        resultList = targetSet.stream().collect(Collectors.toList());
        return resultList;
    }
}