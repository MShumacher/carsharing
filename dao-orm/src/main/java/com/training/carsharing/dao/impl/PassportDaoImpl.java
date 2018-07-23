package com.training.carsharing.dao.impl;

import com.training.carsharing.dao.IPassportDao;
import com.training.carsharing.model.IPassport;
import com.training.carsharing.model.impl.Passport;
import com.training.carsharing.model.impl.Passport_;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Root;
import java.util.List;


@Repository
public class PassportDaoImpl extends AbstractDaoImpl<IPassport, Integer> implements IPassportDao {

    protected PassportDaoImpl() { super(Passport.class); }

    @Override
    public IPassport createEntity() {
        final IPassport passport = new Passport();
        passport.setVersion(IPassport.DEFAULT_VERSION);
        return passport;
    }

    @Override
    public IPassport selectFullInfo(final Integer id) {
        final EntityManager em = getEntityManager();
        final CriteriaBuilder cb = em.getCriteriaBuilder();
        final CriteriaQuery<IPassport> cq = cb.createQuery(IPassport.class);
        final Root<Passport> from = cq.from(Passport.class);
        cq.select(from);

        from.fetch(Passport_.userAccount, JoinType.LEFT);
        cq.where(cb.equal(from.get(Passport_.id),id));

        final List <IPassport> resultList = em.createQuery(cq).getResultList();
        return resultList.isEmpty() ? null : resultList.get(0);
    }

    @Override
    public List<IPassport> selectAllFullInfo() {
        final EntityManager em = getEntityManager();
        final CriteriaBuilder cb = em.getCriteriaBuilder();
        final CriteriaQuery<IPassport> cq = cb.createQuery(IPassport.class);
        final Root<Passport> from = cq.from(Passport.class);
        cq.select(from);

        from.fetch(Passport_.userAccount, JoinType.LEFT);

        final TypedQuery<IPassport> q = em.createQuery(cq);
        return q.getResultList();
    }

//    @Override
//    public long getCount(final PassportFilter filter) {
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
//    public List<IPassport> find(final PassportFilter filter) {
//        final EntityManager em = getEntityManager();
//        final CriteriaBuilder cb = em.getCriteriaBuilder();
//        final CriteriaQuery<IPassport> cq = cb.createQuery(IPassport.class);
//        final Root<model> from = cq.from(model.class);
//        cq.select(from);
//
//        if (filter.getSortColumn() != null) {
//            final SingularAttribute<? super model, ?> sortProperty = toMetamodelFormat(filter.getSortColumn());
//            final Path<?> expression = from.get(sortProperty);
//            cq.orderBy(new OrderImpl(expression, filter.getSortOrder()));
//        }
//
//        final TypedQuery<IPassport> q = em.createQuery(cq);
//        setPaging(filter, q);
//        return q.getResultList();
//    }
//
//    private SingularAttribute<? super model, ?> toMetamodelFormat(final String sortColumn) {
//        switch (sortColumn) {
//            case "created":
//                return Passport_.created;
//            case "updated":
//                return Passport_.updated;
//            case "id":
//                return Passport_.id;
//            case "name":
//                return Passport_.name;
//            default:
//                throw new UnsupportedOperationException("sorting is not supported by column:" + sortColumn);
//        }
//    }

}