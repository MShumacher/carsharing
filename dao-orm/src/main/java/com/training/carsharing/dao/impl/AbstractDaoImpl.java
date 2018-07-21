package com.training.carsharing.dao.impl;

import com.training.carsharing.dao.IAbstractDao;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;
import javax.persistence.criteria.CriteriaQuery;
import java.util.List;

public abstract class AbstractDaoImpl<T, ID> implements IAbstractDao<T, ID> {

    @PersistenceContext
    private EntityManager entityManager;

    private final Class<? extends T> entityClass;

    protected AbstractDaoImpl(final Class<? extends T> entityClass) {
        this.entityClass = entityClass;
    }

//    public Class<? extends T> getEntityClass() {
//        return entityClass;
//    }

    protected EntityManager getEntityManager() { return entityManager; }


    @Override
    public T select(final ID id) {
        return entityManager.find(entityClass, id);
    }

    @Override
    public List<T> selectAll() {
        final CriteriaQuery<? extends T> query = entityManager.getCriteriaBuilder().createQuery(entityClass);
        query.from(entityClass);
        final List<? extends T> lst = entityManager.createQuery(query).getResultList();
        return (List<T>) lst;
    }

    @Override
    public void insert(T entity) throws PersistenceException {
        try {
            entityManager.persist(entity);
        } catch (PersistenceException e) {
            throw new PersistenceException();
        }
    }

    @Override
    public void update(T entity) throws PersistenceException {
        try {
            entity = entityManager.merge(entity);
            entityManager.flush();
        } catch (PersistenceException e) {
            throw new PersistenceException();
        }
    }

    @Override
    public void delete(final ID id) {
        entityManager.createQuery(String.format("delete from %s e where e.id = :id", entityClass.getSimpleName()))
                .setParameter("id", id).executeUpdate();
    }

    @Override
    public void deleteAll() {
        entityManager.createQuery(String.format("delete from %s", entityClass.getSimpleName())).executeUpdate();
        System.out.println(entityClass.getSimpleName());
    }

}
