package com.training.carsharing.repository.customrepository.impl;

import com.training.carsharing.model.impl.Drive;
import com.training.carsharing.model.impl.Drive_;
import com.training.carsharing.repository.customrepository.DriveRepositoryCustom;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;


@Repository
public class DriveRepositoryImpl extends AbstractRepositoryImpl<Drive, Long> implements DriveRepositoryCustom {

    protected DriveRepositoryImpl() {
        super(Drive.class);
    }

    @Override
    public Drive createEntity() {
        final Drive parameter = new Drive();
        parameter.setVersion(Drive.DEFAULT_VERSION);
        return parameter;
    }

    //TODO delete methods
    @Override
    public Drive findOneFullInfo(final Long id) {
        final EntityManager em = getEntityManager();
        final CriteriaBuilder cb = em.getCriteriaBuilder();
        final CriteriaQuery<Drive> cq = cb.createQuery(Drive.class);
        final Root<Drive> from = cq.from(Drive.class);
        cq.select(from);
        cq.where(cb.equal(from.get(Drive_.id), id));

        final List<Drive> resultList = em.createQuery(cq).getResultList();
        return resultList.isEmpty() ? null : resultList.get(0);
    }

    @Override
    public List<Drive> findAllFullInfo() {
        final EntityManager em = getEntityManager();
        final CriteriaBuilder cb = em.getCriteriaBuilder();
        final CriteriaQuery<Drive> cq = cb.createQuery(Drive.class);
        final Root<Drive> from = cq.from(Drive.class);
        cq.select(from);

        final TypedQuery<Drive> q = em.createQuery(cq);
        return q.getResultList();
    }

}