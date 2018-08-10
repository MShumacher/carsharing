package com.training.carsharing.repository.customrepository.impl;

import com.training.carsharing.model.impl.Model;
import com.training.carsharing.model.impl.Model_;
import com.training.carsharing.repository.customrepository.ModelRepositoryCustom;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Root;
import java.util.List;


@Repository
public class ModelRepositoryImpl extends AbstractRepositoryImpl<Model, Integer> implements ModelRepositoryCustom {

    protected ModelRepositoryImpl() {
        super(Model.class);
    }

    @Override
    public Model createEntity() {
        final Model model = new Model();
        model.setVersion(Model.DEFAULT_VERSION);
        return model;
    }

    @Override
    public Model findOneFullInfo(Integer id) {
        final EntityManager em = getEntityManager();
        final CriteriaBuilder cb = em.getCriteriaBuilder();
        final CriteriaQuery<Model> cq = cb.createQuery(Model.class);
        final Root<Model> from = cq.from(Model.class);
        cq.select(from);

        from.fetch(Model_.brand, JoinType.LEFT);

        cq.where(cb.equal(from.get(Model_.id), id));

        final List<Model> resultList = em.createQuery(cq).getResultList();
        return resultList.isEmpty() ? null : resultList.get(0);
    }

    @Override
    public List<Model> findAllFullInfo() {
        final EntityManager em = getEntityManager();
        final CriteriaBuilder cb = em.getCriteriaBuilder();
        final CriteriaQuery<Model> cq = cb.createQuery(Model.class);
        final Root<Model> from = cq.from(Model.class);
        cq.select(from);

        from.fetch(Model_.brand, JoinType.LEFT);

        final TypedQuery<Model> q = em.createQuery(cq);
        List<Model> resultList = q.getResultList();
        return resultList;
    }
}