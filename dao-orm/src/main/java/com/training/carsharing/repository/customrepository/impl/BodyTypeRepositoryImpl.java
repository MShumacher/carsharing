package com.training.carsharing.repository.customrepository.impl;

import com.training.carsharing.model.impl.BodyType;
import com.training.carsharing.model.impl.BodyType_;
import com.training.carsharing.repository.customrepository.BodyTypeRepositoryCustom;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;


@Repository
public class BodyTypeRepositoryImpl extends AbstractRepositoryImpl<BodyType, Long> implements BodyTypeRepositoryCustom {

    protected BodyTypeRepositoryImpl() {
        super(BodyType.class);
    }

    @Override
    public BodyType createEntity() {
        final BodyType parameter = new BodyType();
        parameter.setVersion(BodyType.DEFAULT_VERSION);
        return parameter;
    }

    //TODO delete methods
    @Override
    public BodyType findOneFullInfo(final Long id) {
        final EntityManager em = getEntityManager();
        final CriteriaBuilder cb = em.getCriteriaBuilder();
        final CriteriaQuery<BodyType> cq = cb.createQuery(BodyType.class);
        final Root<BodyType> from = cq.from(BodyType.class);
        cq.select(from);
        cq.where(cb.equal(from.get(BodyType_.id), id));

        final List<BodyType> resultList = em.createQuery(cq).getResultList();
        return resultList.isEmpty() ? null : resultList.get(0);
    }

    @Override
    public List<BodyType> findAllFullInfo() {
        final EntityManager em = getEntityManager();
        final CriteriaBuilder cb = em.getCriteriaBuilder();
        final CriteriaQuery<BodyType> cq = cb.createQuery(BodyType.class);
        final Root<BodyType> from = cq.from(BodyType.class);
        cq.select(from);

        final TypedQuery<BodyType> q = em.createQuery(cq);
        return q.getResultList();
    }
}