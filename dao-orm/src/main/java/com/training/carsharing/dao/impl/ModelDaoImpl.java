package com.training.carsharing.dao.impl;

import com.training.carsharing.dao.IModelDao;
import com.training.carsharing.model.IModel;
import com.training.carsharing.model.IModel;
import com.training.carsharing.model.IModel;
import com.training.carsharing.model.impl.*;
import com.training.carsharing.model.impl.Model_;
import com.training.carsharing.model.impl.Model;
import com.training.carsharing.model.impl.Model_;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Root;
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
        final EntityManager em = getEntityManager();
        final CriteriaBuilder cb = em.getCriteriaBuilder();
        final CriteriaQuery<IModel> cq = cb.createQuery(IModel.class);
        final Root<Model> from = cq.from(Model.class);
        cq.select(from);

        from.fetch(Model_.brand, JoinType.LEFT);

        cq.where(cb.equal(from.get(Model_.id),id));

        final List <IModel> resultList = em.createQuery(cq).getResultList();
        return resultList.isEmpty() ? null : resultList.get(0);
    }

    @Override
    public List<IModel> selectAllFullInfo() {
        final EntityManager em = getEntityManager();
        final CriteriaBuilder cb = em.getCriteriaBuilder();
        final CriteriaQuery<IModel> cq = cb.createQuery(IModel.class);
        final Root<Model> from = cq.from(Model.class);
        cq.select(from);

        from.fetch(Model_.brand, JoinType.LEFT);

        final TypedQuery<IModel> q = em.createQuery(cq);
        List<IModel> resultList = q.getResultList();
        return resultList;
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