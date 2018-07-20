package com.training.carsharing.orm.impl;

import com.training.carsharing.dao.IUserAccount;
import com.training.carsharing.dao.impl.UserAccount;
import com.training.carsharing.orm.IUserAccountDao;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;


@Repository
public class UserAccountDaoImpl extends AbstractDaoImpl<IUserAccount, Integer> implements IUserAccountDao {

    protected UserAccountDaoImpl() {
        super(UserAccount.class);
    }

    @Override
    public IUserAccount createEntity() {
        final UserAccount UserAccount = new UserAccount();
        return UserAccount;
    }

    @Override
    public List<IUserAccount> selectAllFullInfo() {
        final EntityManager em = getEntityManager();
        final CriteriaBuilder cb = em.getCriteriaBuilder();
        final CriteriaQuery<IUserAccount> cq = cb.createQuery(IUserAccount.class);
        final Root<UserAccount> from = cq.from(UserAccount.class);
        cq.select(from);
        final TypedQuery<IUserAccount> q = em.createQuery(cq);
        return q.getResultList();
    }

//    @Override
//    public long getCount(final UserAccountFilter filter) {
//        final EntityManager em = getEntityManager();
//        final CriteriaBuilder cb = em.getCriteriaBuilder();
//        final CriteriaQuery<Long> cq = cb.createQuery(Long.class);
//        final Root<UserAccount> from = cq.from(UserAccount.class);
//        cq.select(cb.count(from));
//        final TypedQuery<Long> q = em.createQuery(cq);
//        return q.getSingleResult();
//    }
//
//    @Override
//    public List<IUserAccount> find(final UserAccountFilter filter) {
//        final EntityManager em = getEntityManager();
//        final CriteriaBuilder cb = em.getCriteriaBuilder();
//        final CriteriaQuery<IUserAccount> cq = cb.createQuery(IUserAccount.class);
//        final Root<UserAccount> from = cq.from(UserAccount.class);
//        cq.select(from);
//
//        if (filter.getSortColumn() != null) {
//            final SingularAttribute<? super UserAccount, ?> sortProperty = toMetamodelFormat(filter.getSortColumn());
//            final Path<?> expression = from.get(sortProperty);
//            cq.orderBy(new OrderImpl(expression, filter.getSortOrder()));
//        }
//
//        final TypedQuery<IUserAccount> q = em.createQuery(cq);
//        setPaging(filter, q);
//        return q.getResultList();
//    }
//
//    private SingularAttribute<? super UserAccount, ?> toMetamodelFormat(final String sortColumn) {
//        switch (sortColumn) {
//            case "created":
//                return UserAccount_.created;
//            case "updated":
//                return UserAccount_.updated;
//            case "id":
//                return UserAccount_.id;
//            case "name":
//                return UserAccount_.name;
//            default:
//                throw new UnsupportedOperationException("sorting is not supported by column:" + sortColumn);
//        }
//    }

}