package com.training.carsharing.dao.impl;

import com.training.carsharing.dao.IDrivingLicenseDao;
import com.training.carsharing.model.IDrivingLicense;
import com.training.carsharing.model.IDrivingLicense;
import com.training.carsharing.model.impl.DrivingLicense;
import com.training.carsharing.model.impl.DrivingLicense_;
import com.training.carsharing.model.impl.DrivingLicense;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Root;
import java.util.List;


@Repository
public class DrivingLicenseDaoImpl extends AbstractDaoImpl<IDrivingLicense, Integer> implements IDrivingLicenseDao {

    protected DrivingLicenseDaoImpl() { super(DrivingLicense.class); }

    @Override
    public IDrivingLicense createEntity() {
        final IDrivingLicense drivingLicense = new DrivingLicense();
        drivingLicense.setVersion(IDrivingLicense.DEFAULT_VERSION);
        return drivingLicense;
    }

    @Override
    public IDrivingLicense selectFullInfo(final Integer id) {
        final EntityManager em = getEntityManager();
        final CriteriaBuilder cb = em.getCriteriaBuilder();
        final CriteriaQuery<IDrivingLicense> cq = cb.createQuery(IDrivingLicense.class);
        final Root<DrivingLicense> from = cq.from(DrivingLicense.class);
        cq.select(from);

        from.fetch(DrivingLicense_.userAccount, JoinType.LEFT);
        cq.where(cb.equal(from.get(DrivingLicense_.id),id));

        final List <IDrivingLicense> resultList = em.createQuery(cq).getResultList();
        return resultList.isEmpty() ? null : resultList.get(0);
    }

    @Override
    public List<IDrivingLicense> selectAllFullInfo() {
        final EntityManager em = getEntityManager();
        final CriteriaBuilder cb = em.getCriteriaBuilder();
        final CriteriaQuery<IDrivingLicense> cq = cb.createQuery(IDrivingLicense.class);
        final Root<DrivingLicense> from = cq.from(DrivingLicense.class);
        cq.select(from);

        from.fetch(DrivingLicense_.userAccount, JoinType.LEFT);

        final TypedQuery<IDrivingLicense> q = em.createQuery(cq);
        return q.getResultList();
    }

//    @Override
//    public long getCount(final DrivingLicenseFilter filter) {
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
//    public List<IDrivingLicense> find(final DrivingLicenseFilter filter) {
//        final EntityManager em = getEntityManager();
//        final CriteriaBuilder cb = em.getCriteriaBuilder();
//        final CriteriaQuery<IDrivingLicense> cq = cb.createQuery(IDrivingLicense.class);
//        final Root<model> from = cq.from(model.class);
//        cq.select(from);
//
//        if (filter.getSortColumn() != null) {
//            final SingularAttribute<? super model, ?> sortProperty = toMetamodelFormat(filter.getSortColumn());
//            final Path<?> expression = from.get(sortProperty);
//            cq.orderBy(new OrderImpl(expression, filter.getSortOrder()));
//        }
//
//        final TypedQuery<IDrivingLicense> q = em.createQuery(cq);
//        setPaging(filter, q);
//        return q.getResultList();
//    }
//
//    private SingularAttribute<? super model, ?> toMetamodelFormat(final String sortColumn) {
//        switch (sortColumn) {
//            case "created":
//                return DrivingLicense_.created;
//            case "updated":
//                return DrivingLicense_.updated;
//            case "id":
//                return DrivingLicense_.id;
//            case "name":
//                return DrivingLicense_.name;
//            default:
//                throw new UnsupportedOperationException("sorting is not supported by column:" + sortColumn);
//        }
//    }

}