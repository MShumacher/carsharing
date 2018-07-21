package com.training.carsharing.dao.impl;

import com.training.carsharing.dao.ICarsPhotoDao;
import com.training.carsharing.model.ICarsPhoto;
import com.training.carsharing.model.impl.CarsPhoto;
import com.training.carsharing.model.impl.CarsPhoto_;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Root;
import java.util.List;


@Repository
public class CarsPhotoDaoImpl extends AbstractDaoImpl<ICarsPhoto, Integer> implements ICarsPhotoDao {

 protected CarsPhotoDaoImpl() { super(CarsPhoto.class); }

    @Override
    public ICarsPhoto createEntity() {
        final ICarsPhoto carsPhoto = new CarsPhoto();
        carsPhoto.setVersion(ICarsPhoto.DEFAULT_VERSION);
        return carsPhoto;
    }

    @Override
    public ICarsPhoto selectFullInfo(final Integer id) {
        final EntityManager em = getEntityManager();
        final CriteriaBuilder cb = em.getCriteriaBuilder();
        final CriteriaQuery<ICarsPhoto> cq = cb.createQuery(ICarsPhoto.class);
        final Root<CarsPhoto> from = cq.from(CarsPhoto.class);
        cq.select(from);

        from.fetch(CarsPhoto_.car, JoinType.LEFT);
        cq.where(cb.equal(from.get(CarsPhoto_.id),id));

        final List <ICarsPhoto> resultList = em.createQuery(cq).getResultList();
        return resultList.isEmpty() ? null : resultList.get(0);
    }

    @Override
    public List<ICarsPhoto> selectAllFullInfo() {
        final EntityManager em = getEntityManager();
        final CriteriaBuilder cb = em.getCriteriaBuilder();
        final CriteriaQuery<ICarsPhoto> cq = cb.createQuery(ICarsPhoto.class);
        final Root<CarsPhoto> from = cq.from(CarsPhoto.class);
        cq.select(from);

        from.fetch(CarsPhoto_.car, JoinType.LEFT);

        final TypedQuery<ICarsPhoto> q = em.createQuery(cq);
        return q.getResultList();
    }


//    @Override
//    public long getCount(final CarsPhotoFilter filter) {
//        final EntityManager em = getEntityManager();
//        final CriteriaBuilder cb = em.getCriteriaBuilder();
//        final CriteriaQuery<Long> cq = cb.createQuery(Long.class);
//        final Root<model> from = cq.from(model.class);
//        cq.select(cb.count(from));
//        final TypedQuery<Long> q = em.createQuery(cq);
//        return q.getSingleResult();
//    }
//
//    @Override
//    public List<ICarsPhoto> find(final CarsPhotoFilter filter) {
//        final EntityManager em = getEntityManager();
//        final CriteriaBuilder cb = em.getCriteriaBuilder();
//        final CriteriaQuery<ICarsPhoto> cq = cb.createQuery(ICarsPhoto.class);
//        final Root<model> from = cq.from(model.class);
//        cq.select(from);
//
//        if (filter.getSortColumn() != null) {
//            final SingularAttribute<? super model, ?> sortProperty = toMetamodelFormat(filter.getSortColumn());
//            final Path<?> expression = from.get(sortProperty);
//            cq.orderBy(new OrderImpl(expression, filter.getSortOrder()));
//        }
//
//        final TypedQuery<ICarsPhoto> q = em.createQuery(cq);
//        setPaging(filter, q);
//        return q.getResultList();
//    }
//
//    private SingularAttribute<? super model, ?> toMetamodelFormat(final String sortColumn) {
//        switch (sortColumn) {
//            case "created":
//                return CarsPhoto_.created;
//            case "updated":
//                return CarsPhoto_.updated;
//            case "id":
//                return CarsPhoto_.id;
//            case "name":
//                return CarsPhoto_.name;
//            default:
//                throw new UnsupportedOperationException("sorting is not supported by column:" + sortColumn);
//        }
//    }

}