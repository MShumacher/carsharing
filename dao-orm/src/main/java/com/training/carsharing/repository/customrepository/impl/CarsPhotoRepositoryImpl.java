package com.training.carsharing.repository.customrepository.impl;

import com.training.carsharing.model.impl.CarsPhoto;
import com.training.carsharing.model.impl.CarsPhoto_;
import com.training.carsharing.repository.customrepository.CarsPhotoRepositoryCustom;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Root;
import java.util.List;


@Repository
public class CarsPhotoRepositoryImpl extends AbstractRepositoryImpl<CarsPhoto, Integer> implements CarsPhotoRepositoryCustom {

 protected CarsPhotoRepositoryImpl() { super(CarsPhoto.class); }

    @Override
    public CarsPhoto createEntity() {
        final CarsPhoto carsPhoto = new CarsPhoto();
        carsPhoto.setVersion(CarsPhoto.DEFAULT_VERSION);
        return carsPhoto;
    }

    @Override
    public CarsPhoto findOneFullInfo(final Integer id) {
        final EntityManager em = getEntityManager();
        final CriteriaBuilder cb = em.getCriteriaBuilder();
        final CriteriaQuery<CarsPhoto> cq = cb.createQuery(CarsPhoto.class);
        final Root<CarsPhoto> from = cq.from(CarsPhoto.class);
        cq.select(from);

        from.fetch(CarsPhoto_.car, JoinType.LEFT);
        cq.where(cb.equal(from.get(CarsPhoto_.id),id));

        final List <CarsPhoto> resultList = em.createQuery(cq).getResultList();
        return resultList.isEmpty() ? null : resultList.get(0);
    }

    @Override
    public List<CarsPhoto> findAllFullInfo() {
        final EntityManager em = getEntityManager();
        final CriteriaBuilder cb = em.getCriteriaBuilder();
        final CriteriaQuery<CarsPhoto> cq = cb.createQuery(CarsPhoto.class);
        final Root<CarsPhoto> from = cq.from(CarsPhoto.class);
        cq.select(from);

        from.fetch(CarsPhoto_.car, JoinType.LEFT);

        final TypedQuery<CarsPhoto> q = em.createQuery(cq);
        return q.getResultList();
    }
}