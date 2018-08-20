package com.training.carsharing.repository.customrepository.impl;

import com.training.carsharing.model.impl.UserAccount;
import com.training.carsharing.model.impl.UserAccount_;
import com.training.carsharing.repository.customrepository.UserAccountRepositoryCustom;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Root;
import java.util.List;

@Repository
public class UserAccountRepositoryImpl extends AbstractRepositoryImpl<UserAccount, Long> implements UserAccountRepositoryCustom {

    protected UserAccountRepositoryImpl() {
        super(UserAccount.class);
    }

    @Override
    public UserAccount createEntity() {
        final UserAccount user = new UserAccount();
        user.setVersion(user.DEFAULT_VERSION);
        return user;
    }

    @Override
    public UserAccount findOneFullInfo(final Long id) {
        final EntityManager em = getEntityManager();
        final CriteriaBuilder cb = em.getCriteriaBuilder();
        final CriteriaQuery<UserAccount> cq = cb.createQuery(UserAccount.class);
        final Root<UserAccount> from = cq.from(UserAccount.class);
        cq.select(from);

        from.fetch(UserAccount_.passport, JoinType.LEFT);
        from.fetch(UserAccount_.drivingLicense, JoinType.LEFT);
        cq.where(cb.equal(from.get(UserAccount_.id), id));

        final List<UserAccount> resultList = em.createQuery(cq).getResultList();
        return resultList.isEmpty() ? null : resultList.get(0);
    }

    @Override
    public List<UserAccount> findAllFullInfo() {
        final EntityManager em = getEntityManager();
        final CriteriaBuilder cb = em.getCriteriaBuilder();
        final CriteriaQuery<UserAccount> cq = cb.createQuery(UserAccount.class);
        final Root<UserAccount> from = cq.from(UserAccount.class);
        cq.select(from);

        from.fetch(UserAccount_.passport, JoinType.LEFT);
        from.fetch(UserAccount_.drivingLicense, JoinType.LEFT);

        final TypedQuery<UserAccount> q = em.createQuery(cq);
        return q.getResultList();
    }
}