package com.training.carsharing.repository.customrepository.impl;

import com.training.carsharing.model.impl.Calendar;
import com.training.carsharing.model.impl.Calendar_;
import com.training.carsharing.repository.customrepository.CalendarRepositoryCustom;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Root;
import java.util.List;


@Repository
public class CalendarRepositoryImpl extends AbstractRepositoryImpl<Calendar, Integer> implements CalendarRepositoryCustom {

    protected CalendarRepositoryImpl() {
        super(Calendar.class);
    }

    @Override
    public Calendar createEntity() {
        final Calendar calendar = new Calendar();
        calendar.setVersion(Calendar.DEFAULT_VERSION);
        return calendar;
    }

    @Override
    public Calendar findOneFullInfo(final Integer id) {
        final EntityManager em = getEntityManager();
        final CriteriaBuilder cb = em.getCriteriaBuilder();
        final CriteriaQuery<Calendar> cq = cb.createQuery(Calendar.class);
        final Root<Calendar> from = cq.from(Calendar.class);
        cq.select(from);

        from.fetch(Calendar_.car, JoinType.LEFT);
        from.fetch(Calendar_.renter, JoinType.LEFT);
        cq.where(cb.equal(from.get(Calendar_.id), id));

        final List<Calendar> resultList = em.createQuery(cq).getResultList();
        return resultList.isEmpty() ? null : resultList.get(0);
    }

    @Override
    public List<Calendar> findAllFullInfo() {
        final EntityManager em = getEntityManager();
        final CriteriaBuilder cb = em.getCriteriaBuilder();
        final CriteriaQuery<Calendar> cq = cb.createQuery(Calendar.class);
        final Root<Calendar> from = cq.from(Calendar.class);
        cq.select(from);

        from.fetch(Calendar_.car, JoinType.LEFT);
        from.fetch(Calendar_.renter, JoinType.LEFT);

        final TypedQuery<Calendar> q = em.createQuery(cq);
        return q.getResultList();
    }
}