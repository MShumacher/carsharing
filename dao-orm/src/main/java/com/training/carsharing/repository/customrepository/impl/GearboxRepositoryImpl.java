package com.training.carsharing.repository.customrepository.impl;

import com.training.carsharing.model.impl.Gearbox;
import com.training.carsharing.model.impl.Gearbox_;
import com.training.carsharing.repository.customrepository.GearboxRepositoryCustom;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;


@Repository
public class GearboxRepositoryImpl extends AbstractRepositoryImpl<Gearbox, Integer> implements GearboxRepositoryCustom {

    protected GearboxRepositoryImpl() {
        super(Gearbox.class);
    }

    @Override
    public Gearbox createEntity() {
        final Gearbox parameter = new Gearbox();
        parameter.setVersion(Gearbox.DEFAULT_VERSION);
        return parameter;
    }

    //TODO delete methods
    @Override
    public Gearbox findOneFullInfo(final Integer id) {
        final EntityManager em = getEntityManager();
        final CriteriaBuilder cb = em.getCriteriaBuilder();
        final CriteriaQuery<Gearbox> cq = cb.createQuery(Gearbox.class);
        final Root<Gearbox> from = cq.from(Gearbox.class);
        cq.select(from);
        cq.where(cb.equal(from.get(Gearbox_.id), id));

        final List<Gearbox> resultList = em.createQuery(cq).getResultList();
        return resultList.isEmpty() ? null : resultList.get(0);
    }

    @Override
    public List<Gearbox> findAllFullInfo() {
        final EntityManager em = getEntityManager();
        final CriteriaBuilder cb = em.getCriteriaBuilder();
        final CriteriaQuery<Gearbox> cq = cb.createQuery(Gearbox.class);
        final Root<Gearbox> from = cq.from(Gearbox.class);
        cq.select(from);

        final TypedQuery<Gearbox> q = em.createQuery(cq);
        return q.getResultList();
    }

}