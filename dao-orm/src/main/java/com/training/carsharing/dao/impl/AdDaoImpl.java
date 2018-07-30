package com.training.carsharing.dao.impl;

import com.training.carsharing.dao.IAdDao;
import com.training.carsharing.model.impl.Ad;
import com.training.carsharing.model.impl.Ad_;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Root;
import java.util.List;


@Repository
public class AdDaoImpl extends AbstractDaoImpl<Ad, Integer> implements IAdDao {

    protected AdDaoImpl() {
        super(Ad.class);
    }

    @Override
    public Ad createEntity() {
        final Ad ad = new Ad();
        ad.setVersion(Ad.DEFAULT_VERSION);
        return ad;
    }

    @Override
    public Ad selectFullInfo(final Integer id) {
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
    public List<Ad> selectAllFullInfo() {
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

//    @Override
//    public long getCount(final AdFilter filter) {
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
//    public List<Ad> find(final AdFilter filter) {
//        final EntityManager em = getEntityManager();
//        final CriteriaBuilder cb = em.getCriteriaBuilder();
//        final CriteriaQuery<Ad> cq = cb.createQuery(Ad.class);
//        final Root<model> from = cq.from(model.class);
//        cq.select(from);
//
//        if (filter.getSortColumn() != null) {
//            final SingularAttribute<? super model, ?> sortProperty = toMetamodelFormat(filter.getSortColumn());
//            final Path<?> expression = from.get(sortProperty);
//            cq.orderBy(new OrderImpl(expression, filter.getSortOrder()));
//        }
//
//        final TypedQuery<Ad> q = em.createQuery(cq);
//        setPaging(filter, q);
//        return q.getResultList();
//    }
//
//    private SingularAttribute<? super model, ?> toMetamodelFormat(final String sortColumn) {
//        switch (sortColumn) {
//            case "created":
//                return Ad_.created;
//            case "updated":
//                return Ad_.updated;
//            case "id":
//                return Ad_.id;
//            case "name":
//                return Ad_.name;
//            default:
//                throw new UnsupportedOperationException("sorting is not supported by column:" + sortColumn);
//        }
//    }

}