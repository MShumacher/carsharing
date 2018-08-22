package com.training.carsharing.repository.customrepository.impl;

import com.training.carsharing.model.impl.Fuel;
import com.training.carsharing.model.impl.Fuel_;
import com.training.carsharing.repository.customrepository.FuelRepositoryCustom;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;


@Repository
public class FuelRepositoryImpl extends AbstractRepositoryImpl<Fuel, Long> implements FuelRepositoryCustom {

    protected FuelRepositoryImpl() {
        super(Fuel.class);
    }

    //TODO delete methods
    @Override
    public Fuel findOneFullInfo(final Long id) {
        final EntityManager em = getEntityManager();
        final CriteriaBuilder cb = em.getCriteriaBuilder();
        final CriteriaQuery<Fuel> cq = cb.createQuery(Fuel.class);
        final Root<Fuel> from = cq.from(Fuel.class);
        cq.select(from);
        cq.where(cb.equal(from.get(Fuel_.id), id));

        final List<Fuel> resultList = em.createQuery(cq).getResultList();
        return resultList.isEmpty() ? null : resultList.get(0);
    }

    @Override
    public List<Fuel> findAllFullInfo() {
        final EntityManager em = getEntityManager();
        final CriteriaBuilder cb = em.getCriteriaBuilder();
        final CriteriaQuery<Fuel> cq = cb.createQuery(Fuel.class);
        final Root<Fuel> from = cq.from(Fuel.class);
        cq.select(from);

        final TypedQuery<Fuel> q = em.createQuery(cq);
        return q.getResultList();
    }

}