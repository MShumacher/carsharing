package com.training.carsharing.repository.customrepository.impl;

import com.training.carsharing.model.impl.Brand;
import com.training.carsharing.model.impl.Brand_;
import com.training.carsharing.repository.customrepository.BrandRepositoryCustom;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;


@Repository
public class BrandRepositoryImpl extends AbstractRepositoryImpl<Brand, Long> implements BrandRepositoryCustom {

    protected BrandRepositoryImpl() {
        super(Brand.class);
    }

    @Override
    public Brand createEntity() {
        final Brand parameter = new Brand();
        parameter.setVersion(Brand.DEFAULT_VERSION);
        return parameter;
    }

    //TODO delete methods
    @Override
    public Brand findOneFullInfo(final Long id) {
        final EntityManager em = getEntityManager();
        final CriteriaBuilder cb = em.getCriteriaBuilder();
        final CriteriaQuery<Brand> cq = cb.createQuery(Brand.class);
        final Root<Brand> from = cq.from(Brand.class);
        cq.select(from);
        cq.where(cb.equal(from.get(Brand_.id), id));

        final List<Brand> resultList = em.createQuery(cq).getResultList();
        return resultList.isEmpty() ? null : resultList.get(0);
    }

    @Override
    public List<Brand> findAllFullInfo() {
        final EntityManager em = getEntityManager();
        final CriteriaBuilder cb = em.getCriteriaBuilder();
        final CriteriaQuery<Brand> cq = cb.createQuery(Brand.class);
        final Root<Brand> from = cq.from(Brand.class);
        cq.select(from);

        final TypedQuery<Brand> q = em.createQuery(cq);
        return q.getResultList();
    }

}