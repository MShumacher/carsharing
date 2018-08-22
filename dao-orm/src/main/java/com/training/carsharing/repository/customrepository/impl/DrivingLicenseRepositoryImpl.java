package com.training.carsharing.repository.customrepository.impl;

import com.training.carsharing.model.impl.DrivingLicense;
import com.training.carsharing.model.impl.DrivingLicense_;
import com.training.carsharing.repository.customrepository.DrivingLicenseRepositoryCustom;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Root;
import java.util.List;


@Repository
public class DrivingLicenseRepositoryImpl extends AbstractRepositoryImpl<DrivingLicense, Long> implements DrivingLicenseRepositoryCustom {

    protected DrivingLicenseRepositoryImpl() {
        super(DrivingLicense.class);
    }

    @Override
    public DrivingLicense findOneFullInfo(final Long id) {
        final EntityManager em = getEntityManager();
        final CriteriaBuilder cb = em.getCriteriaBuilder();
        final CriteriaQuery<DrivingLicense> cq = cb.createQuery(DrivingLicense.class);
        final Root<DrivingLicense> from = cq.from(DrivingLicense.class);
        cq.select(from);

        from.fetch(DrivingLicense_.userAccount, JoinType.LEFT);
        cq.where(cb.equal(from.get(DrivingLicense_.id), id));

        final List<DrivingLicense> resultList = em.createQuery(cq).getResultList();
        return resultList.isEmpty() ? null : resultList.get(0);
    }

    @Override
    public List<DrivingLicense> findAllFullInfo() {
        final EntityManager em = getEntityManager();
        final CriteriaBuilder cb = em.getCriteriaBuilder();
        final CriteriaQuery<DrivingLicense> cq = cb.createQuery(DrivingLicense.class);
        final Root<DrivingLicense> from = cq.from(DrivingLicense.class);
        cq.select(from);

        from.fetch(DrivingLicense_.userAccount, JoinType.LEFT);

        final TypedQuery<DrivingLicense> q = em.createQuery(cq);
        return q.getResultList();
    }
}