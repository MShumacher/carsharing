package com.training.carsharing.orm.impl;

import com.training.carsharing.dao.IModel;
import com.training.carsharing.dao.impl.Model;
import com.training.carsharing.orm.IModelDao;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;


@Repository
public class ModelDaoImpl extends AbstractDaoImpl<IModel, Integer> implements IModelDao {

    protected ModelDaoImpl() {
        super(Model.class);
    }

    @Override
    public IModel createEntity() {
        final Model Model = new Model();
        return Model;
    }

    @Override
    public List<IModel> selectAllFullInfo() {
        final EntityManager em = getEntityManager();
        final CriteriaBuilder cb = em.getCriteriaBuilder();
        final CriteriaQuery<IModel> cq = cb.createQuery(IModel.class);
        final Root<Model> from = cq.from(Model.class);
        cq.select(from);
        final TypedQuery<IModel> q = em.createQuery(cq);
        return q.getResultList();
    }

//    @Override
//    public long getCount(final ModelFilter filter) {
//        final EntityManager em = getEntityManager();
//        final CriteriaBuilder cb = em.getCriteriaBuilder();
//        final CriteriaQuery<Long> cq = cb.createQuery(Long.class);
//        final Root<Model> from = cq.from(Model.class);
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
//        final Root<Model> from = cq.from(Model.class);
//        cq.select(from);
//
//        if (filter.getSortColumn() != null) {
//            final SingularAttribute<? super Model, ?> sortProperty = toMetamodelFormat(filter.getSortColumn());
//            final Path<?> expression = from.get(sortProperty);
//            cq.orderBy(new OrderImpl(expression, filter.getSortOrder()));
//        }
//
//        final TypedQuery<IModel> q = em.createQuery(cq);
//        setPaging(filter, q);
//        return q.getResultList();
//    }
//
//    private SingularAttribute<? super Model, ?> toMetamodelFormat(final String sortColumn) {
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