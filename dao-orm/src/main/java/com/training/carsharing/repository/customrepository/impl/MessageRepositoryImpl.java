package com.training.carsharing.repository.customrepository.impl;

import com.training.carsharing.model.impl.Message;
import com.training.carsharing.model.impl.Message_;
import com.training.carsharing.repository.customrepository.MessageRepositoryCustom;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Root;
import java.util.List;


@Repository
public class MessageRepositoryImpl extends AbstractRepositoryImpl<Message, Integer> implements MessageRepositoryCustom {

    protected MessageRepositoryImpl() {
        super(Message.class);
    }

    @Override
    public Message createEntity() {
        final Message car = new Message();
        car.setVersion(Message.DEFAULT_VERSION);
        return car;
    }

    @Override
    public Message findOneFullInfo(final Integer id) {
        final EntityManager em = getEntityManager();
        final CriteriaBuilder cb = em.getCriteriaBuilder();
        final CriteriaQuery<Message> cq = cb.createQuery(Message.class);
        final Root<Message> from = cq.from(Message.class);
        cq.select(from);

        from.fetch(Message_.ad, JoinType.LEFT);
        from.fetch(Message_.sender, JoinType.LEFT);
        from.fetch(Message_.recipient, JoinType.LEFT);

        cq.where(cb.equal(from.get(Message_.id), id));

        final List<Message> resultList = em.createQuery(cq).getResultList();
        return resultList.isEmpty() ? null : resultList.get(0);
    }

    @Override
    public List<Message> findAllFullInfo() {
        final EntityManager em = getEntityManager();
        final CriteriaBuilder cb = em.getCriteriaBuilder();
        final CriteriaQuery<Message> cq = cb.createQuery(Message.class);
        final Root<Message> from = cq.from(Message.class);
        cq.select(from);

        from.fetch(Message_.ad, JoinType.LEFT);
        from.fetch(Message_.sender, JoinType.LEFT);
        from.fetch(Message_.recipient, JoinType.LEFT);

        final TypedQuery<Message> q = em.createQuery(cq);
        List<Message> resultList = q.getResultList();
        return resultList;
    }
}