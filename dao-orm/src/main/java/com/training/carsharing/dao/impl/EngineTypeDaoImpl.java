package com.training.carsharing.dao.impl;

import com.training.carsharing.dao.IEngineTypeDao;
import com.training.carsharing.model.IEngineType;
import com.training.carsharing.model.impl.EngineType;
import com.training.carsharing.model.impl.EngineType_;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Root;
import java.util.List;


@Repository
public class EngineTypeDaoImpl extends AbstractDaoImpl<IEngineType, Integer> implements IEngineTypeDao {

    protected EngineTypeDaoImpl() { super(EngineType.class); }

    @Override
    public IEngineType createEntity() {
        final IEngineType model = new EngineType();
        model.setVersion(IEngineType.DEFAULT_VERSION);
        return model;
    }

    @Override
    public IEngineType selectFullInfo(Integer id) {
        final EntityManager em = getEntityManager();
        final CriteriaBuilder cb = em.getCriteriaBuilder();
        final CriteriaQuery<IEngineType> cq = cb.createQuery(IEngineType.class);
        final Root<EngineType> from = cq.from(EngineType.class);
        cq.select(from);

        from.fetch(EngineType_.fuel, JoinType.LEFT);

        cq.where(cb.equal(from.get(EngineType_.id),id));

        final List <IEngineType> resultList = em.createQuery(cq).getResultList();
        return resultList.isEmpty() ? null : resultList.get(0);
    }

    @Override
    public List<IEngineType> selectAllFullInfo() {
        final EntityManager em = getEntityManager();
        final CriteriaBuilder cb = em.getCriteriaBuilder();
        final CriteriaQuery<IEngineType> cq = cb.createQuery(IEngineType.class);
        final Root<EngineType> from = cq.from(EngineType.class);
        cq.select(from);

        from.fetch(EngineType_.fuel, JoinType.LEFT);

        final TypedQuery<IEngineType> q = em.createQuery(cq);
        List<IEngineType> resultList = q.getResultList();
        return resultList;
    }

//    @Override
//    public long getCount(final EngineTypeFilter filter) {
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
//    public List<IEngineType> find(final EngineTypeFilter filter) {
//        final EntityManager em = getEntityManager();
//        final CriteriaBuilder cb = em.getCriteriaBuilder();
//        final CriteriaQuery<IEngineType> cq = cb.createQuery(IEngineType.class);
//        final Root<model> from = cq.from(model.class);
//        cq.select(from);
//
//        if (filter.getSortColumn() != null) {
//            final SingularAttribute<? super model, ?> sortProperty = toMetamodelFormat(filter.getSortColumn());
//            final Path<?> expression = from.get(sortProperty);
//            cq.orderBy(new OrderImpl(expression, filter.getSortOrder()));
//        }
//
//        final TypedQuery<IEngineType> q = em.createQuery(cq);
//        setPaging(filter, q);
//        return q.getResultList();
//    }
//
//    private SingularAttribute<? super model, ?> toMetamodelFormat(final String sortColumn) {
//        switch (sortColumn) {
//            case "created":
//                return EngineType_.created;
//            case "updated":
//                return EngineType_.updated;
//            case "id":
//                return EngineType_.id;
//            case "name":
//                return EngineType_.name;
//            default:
//                throw new UnsupportedOperationException("sorting is not supported by column:" + sortColumn);
//        }
//    }

}