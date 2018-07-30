package com.training.carsharing.dao.impl;

import com.training.carsharing.dao.IBodyTypeDao;
import com.training.carsharing.model.impl.BodyType;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public class BodyTypeImpl extends AbstractDaoImpl<BodyType, Integer> implements IBodyTypeDao {

    protected BodyTypeImpl() {
        super(BodyType.class);
    }

    @Override
    public BodyType createEntity() {
        final BodyType parameter = new BodyType();
        parameter.setVersion(BodyType.DEFAULT_VERSION);
        return parameter;
    }

    @Override
    public BodyType selectFullInfo(Integer id) {
        return select(id);
    }

    @Override
    public List<BodyType> selectAllFullInfo() {
        return selectAll();
    }

//    @Override
//    public long getCount(final ParameterFilter filter) {
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
//    public List<BodyType> find(final ParameterFilter filter) {
//        final EntityManager em = getEntityManager();
//        final CriteriaBuilder cb = em.getCriteriaBuilder();
//        final CriteriaQuery<BodyType> cq = cb.createQuery(BodyType.class);
//        final Root<model> from = cq.from(model.class);
//        cq.select(from);
//
//        if (filter.getSortColumn() != null) {
//            final SingularAttribute<? super model, ?> sortProperty = toMetamodelFormat(filter.getSortColumn());
//            final Path<?> expression = from.get(sortProperty);
//            cq.orderBy(new OrderImpl(expression, filter.getSortOrder()));
//        }
//
//        final TypedQuery<BodyType> q = em.createQuery(cq);
//        setPaging(filter, q);
//        return q.getResultList();
//    }
//
//    private SingularAttribute<? super model, ?> toMetamodelFormat(final String sortColumn) {
//        switch (sortColumn) {
//            case "created":
//                return Parameter_.created;
//            case "updated":
//                return Parameter_.updated;
//            case "id":
//                return Parameter_.id;
//            case "name":
//                return Parameter_.name;
//            default:
//                throw new UnsupportedOperationException("sorting is not supported by column:" + sortColumn);
//        }
//    }

}