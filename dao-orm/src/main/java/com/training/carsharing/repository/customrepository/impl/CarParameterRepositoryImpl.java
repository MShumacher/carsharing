package com.training.carsharing.repository.customrepository.impl;

import com.training.carsharing.model.impl.CarParameter;
import com.training.carsharing.model.impl.CarParameter_;
import com.training.carsharing.repository.customrepository.CarParameterRepositoryCustom;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;


@Repository
public class CarParameterRepositoryImpl extends AbstractRepositoryImpl<CarParameter, Long> implements CarParameterRepositoryCustom {

    protected CarParameterRepositoryImpl() { super(CarParameter.class); }

    //TODO delete methods
    @Override
    public CarParameter findOneFullInfo(final Long id) {
        final EntityManager em = getEntityManager();
        final CriteriaBuilder cb = em.getCriteriaBuilder();
        final CriteriaQuery<CarParameter> cq = cb.createQuery(CarParameter.class);
        final Root<CarParameter> from = cq.from(CarParameter.class);
        cq.select(from);
        cq.where(cb.equal(from.get(CarParameter_.id), id));

        final List<CarParameter> resultList = em.createQuery(cq).getResultList();
        return resultList.isEmpty() ? null : resultList.get(0);
    }

    @Override
    public List<CarParameter> findAllFullInfo() {
        final EntityManager em = getEntityManager();
        final CriteriaBuilder cb = em.getCriteriaBuilder();
        final CriteriaQuery<CarParameter> cq = cb.createQuery(CarParameter.class);
        final Root<CarParameter> from = cq.from(CarParameter.class);
        cq.select(from);

        final TypedQuery<CarParameter> q = em.createQuery(cq);
        return q.getResultList();
    }

}