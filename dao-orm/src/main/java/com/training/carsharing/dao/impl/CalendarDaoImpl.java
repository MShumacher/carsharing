package com.training.carsharing.dao.impl;

import com.training.carsharing.dao.ICalendarDao;
import com.training.carsharing.model.ICalendar;
import com.training.carsharing.model.impl.Calendar;
import com.training.carsharing.model.impl.Calendar_;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Root;
import java.util.List;


@Repository
public class CalendarDaoImpl extends AbstractDaoImpl<ICalendar, Integer> implements ICalendarDao {

 protected CalendarDaoImpl() { super(Calendar.class); }

    @Override
    public ICalendar createEntity() {
        final ICalendar calendar = new Calendar();
        calendar.setVersion(ICalendar.DEFAULT_VERSION);
        return calendar;
    }

    @Override
    public ICalendar selectFullInfo(final Integer id) {
        final EntityManager em = getEntityManager();
        final CriteriaBuilder cb = em.getCriteriaBuilder();
        final CriteriaQuery<ICalendar> cq = cb.createQuery(ICalendar.class);
        final Root<Calendar> from = cq.from(Calendar.class);
        cq.select(from);

        from.fetch(Calendar_.car, JoinType.LEFT);
        from.fetch(Calendar_.renter, JoinType.LEFT);
        cq.where(cb.equal(from.get(Calendar_.id),id));

        final List <ICalendar> resultList = em.createQuery(cq).getResultList();
        return resultList.isEmpty() ? null : resultList.get(0);
    }

    @Override
    public List<ICalendar> selectAllFullInfo() {
        final EntityManager em = getEntityManager();
        final CriteriaBuilder cb = em.getCriteriaBuilder();
        final CriteriaQuery<ICalendar> cq = cb.createQuery(ICalendar.class);
        final Root<Calendar> from = cq.from(Calendar.class);
        cq.select(from);

        from.fetch(Calendar_.car, JoinType.LEFT);
        from.fetch(Calendar_.renter, JoinType.LEFT);

        final TypedQuery<ICalendar> q = em.createQuery(cq);
        return q.getResultList();
    }


//    @Override
//    public long getCount(final CalendarFilter filter) {
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
//    public List<ICalendar> find(final CalendarFilter filter) {
//        final EntityManager em = getEntityManager();
//        final CriteriaBuilder cb = em.getCriteriaBuilder();
//        final CriteriaQuery<ICalendar> cq = cb.createQuery(ICalendar.class);
//        final Root<model> from = cq.from(model.class);
//        cq.select(from);
//
//        if (filter.getSortColumn() != null) {
//            final SingularAttribute<? super model, ?> sortProperty = toMetamodelFormat(filter.getSortColumn());
//            final Path<?> expression = from.get(sortProperty);
//            cq.orderBy(new OrderImpl(expression, filter.getSortOrder()));
//        }
//
//        final TypedQuery<ICalendar> q = em.createQuery(cq);
//        setPaging(filter, q);
//        return q.getResultList();
//    }
//
//    private SingularAttribute<? super model, ?> toMetamodelFormat(final String sortColumn) {
//        switch (sortColumn) {
//            case "created":
//                return Calendar_.created;
//            case "updated":
//                return Calendar_.updated;
//            case "id":
//                return Calendar_.id;
//            case "name":
//                return Calendar_.name;
//            default:
//                throw new UnsupportedOperationException("sorting is not supported by column:" + sortColumn);
//        }
//    }

}