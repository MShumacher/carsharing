package com.training.carsharing.repository.customrepository.impl;

import com.training.carsharing.model.impl.EngineType;
import com.training.carsharing.model.impl.EngineType_;
import com.training.carsharing.repository.customrepository.EngineTypeRepositoryCustom;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Root;
import java.util.List;


@Repository
public class EngineTypeRepositoryImpl extends AbstractRepositoryImpl<EngineType, Integer> implements EngineTypeRepositoryCustom {

    protected EngineTypeRepositoryImpl() {
        super(EngineType.class);
    }

    @Override
    public EngineType createEntity() {
        final EngineType model = new EngineType();
        model.setVersion(EngineType.DEFAULT_VERSION);
        return model;
    }

    @Override
    public EngineType findOneFullInfo(Integer id) {
        final EntityManager em = getEntityManager();
        final CriteriaBuilder cb = em.getCriteriaBuilder();
        final CriteriaQuery<EngineType> cq = cb.createQuery(EngineType.class);
        final Root<EngineType> from = cq.from(EngineType.class);
        cq.select(from);

        from.fetch(EngineType_.fuel, JoinType.LEFT);

        cq.where(cb.equal(from.get(EngineType_.id), id));

        final List<EngineType> resultList = em.createQuery(cq).getResultList();
        return resultList.isEmpty() ? null : resultList.get(0);
    }

    @Override
    public List<EngineType> findAllFullInfo() {
        final EntityManager em = getEntityManager();
        final CriteriaBuilder cb = em.getCriteriaBuilder();
        final CriteriaQuery<EngineType> cq = cb.createQuery(EngineType.class);
        final Root<EngineType> from = cq.from(EngineType.class);
        cq.select(from);

        from.fetch(EngineType_.fuel, JoinType.LEFT);

        final TypedQuery<EngineType> q = em.createQuery(cq);
        List<EngineType> resultList = q.getResultList();
        return resultList;
    }
}