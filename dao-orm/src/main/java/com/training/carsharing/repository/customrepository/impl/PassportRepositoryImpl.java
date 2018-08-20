package com.training.carsharing.repository.customrepository.impl;

import com.training.carsharing.model.impl.Passport;
import com.training.carsharing.model.impl.Passport_;
import com.training.carsharing.repository.customrepository.PassportRepositoryCustom;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Root;
import java.util.List;


@Repository
public class PassportRepositoryImpl extends AbstractRepositoryImpl<Passport, Long> implements PassportRepositoryCustom {

    protected PassportRepositoryImpl() {
        super(Passport.class);
    }

    @Override
    public Passport createEntity() {
        final Passport passport = new Passport();
        passport.setVersion(Passport.DEFAULT_VERSION);
        return passport;
    }

    @Override
    public Passport findOneFullInfo(final Long id) {
        final EntityManager em = getEntityManager();
        final CriteriaBuilder cb = em.getCriteriaBuilder();
        final CriteriaQuery<Passport> cq = cb.createQuery(Passport.class);
        final Root<Passport> from = cq.from(Passport.class);
        cq.select(from);

        from.fetch(Passport_.userAccount, JoinType.LEFT);
        cq.where(cb.equal(from.get(Passport_.id), id));

        final List<Passport> resultList = em.createQuery(cq).getResultList();
        return resultList.isEmpty() ? null : resultList.get(0);
    }

    @Override
    public List<Passport> findAllFullInfo() {
        final EntityManager em = getEntityManager();
        final CriteriaBuilder cb = em.getCriteriaBuilder();
        final CriteriaQuery<Passport> cq = cb.createQuery(Passport.class);
        final Root<Passport> from = cq.from(Passport.class);
        cq.select(from);

        from.fetch(Passport_.userAccount, JoinType.LEFT);

        final TypedQuery<Passport> q = em.createQuery(cq);
        return q.getResultList();
    }
}