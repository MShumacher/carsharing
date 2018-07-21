package com.training.carsharing.dao.impl;

import com.training.carsharing.dao.IModelDao;
import com.training.carsharing.model.IModel;
import com.training.carsharing.model.impl.Model;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public class ModelDaoImpl extends AbstractDaoImpl<IModel, Integer> implements IModelDao {

    protected ModelDaoImpl() { super(Model.class); }

    @Override
    public IModel createEntity() {
        final IModel model = new Model();
        model.setVersion(IModel.DEFAULT_VERSION);
        return model;
    }

    @Override
    public IModel selectFullInfo(Integer id) {
        return select(id);
    }

    @Override
    public List<IModel> selectAllFullInfo() {
        return selectAll();
    }

//    @Override
//    public long getCount(final ModelFilter filter) {
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
//    public List<IModel> find(final ModelFilter filter) {
//        final EntityManager em = getEntityManager();
//        final CriteriaBuilder cb = em.getCriteriaBuilder();
//        final CriteriaQuery<IModel> cq = cb.createQuery(IModel.class);
//        final Root<model> from = cq.from(model.class);
//        cq.select(from);
//
//        if (filter.getSortColumn() != null) {
//            final SingularAttribute<? super model, ?> sortProperty = toMetamodelFormat(filter.getSortColumn());
//            final Path<?> expression = from.get(sortProperty);
//            cq.orderBy(new OrderImpl(expression, filter.getSortOrder()));
//        }
//
//        final TypedQuery<IModel> q = em.createQuery(cq);
//        setPaging(filter, q);
//        return q.getResultList();
//    }
//
//    private SingularAttribute<? super model, ?> toMetamodelFormat(final String sortColumn) {
//        switch (sortColumn) {
//            case "created":
//                return Model_.created;
//            case "updated":
//                return Model_.updated;
//            case "id":
//                return Model_.id;
//            case "name":
//                return Model_.name;
//            default:
//                throw new UnsupportedOperationException("sorting is not supported by column:" + sortColumn);
//        }
//    }

}