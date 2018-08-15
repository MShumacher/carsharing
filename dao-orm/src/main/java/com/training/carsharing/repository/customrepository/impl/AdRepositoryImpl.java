package com.training.carsharing.repository.customrepository.impl;

import com.training.carsharing.model.impl.Ad;
import com.training.carsharing.model.impl.Ad_;
import com.training.carsharing.repository.customrepository.AdRepositoryCustom;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Root;
import java.util.List;


@Repository
public class AdRepositoryImpl extends AbstractRepositoryImpl<Ad, Integer> implements AdRepositoryCustom {

    protected AdRepositoryImpl() {
        super(Ad.class);
    }

    @Override
    public Ad createEntity() {
        final Ad ad = new Ad();
        ad.setVersion(Ad.DEFAULT_VERSION);
        return ad;
    }

    @Override
    public Ad findOneFullInfo(final Integer id) {
        final EntityManager em = getEntityManager();
        final CriteriaBuilder cb = em.getCriteriaBuilder();
        final CriteriaQuery<Ad> cq = cb.createQuery(Ad.class);
        final Root<Ad> from = cq.from(Ad.class);
        cq.select(from);

        from.fetch(Ad_.car, JoinType.LEFT);
        from.fetch(Ad_.userAccount, JoinType.LEFT);
        cq.where(cb.equal(from.get(Ad_.id), id));

        final List<Ad> resultList = em.createQuery(cq).getResultList();
        return resultList.isEmpty() ? null : resultList.get(0);
    }

    @Override
    public List<Ad> findAllFullInfo() {
        final EntityManager em = getEntityManager();
        final CriteriaBuilder cb = em.getCriteriaBuilder();
        final CriteriaQuery<Ad> cq = cb.createQuery(Ad.class);
        final Root<Ad> from = cq.from(Ad.class);
        cq.select(from);

        from.fetch(Ad_.car, JoinType.LEFT);
        from.fetch(Ad_.userAccount, JoinType.LEFT);

        final TypedQuery<Ad> q = em.createQuery(cq);
        return q.getResultList();
    }
}