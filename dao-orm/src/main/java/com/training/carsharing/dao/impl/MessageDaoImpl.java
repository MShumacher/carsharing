package com.training.carsharing.dao.impl;

import com.training.carsharing.dao.IMessageDao;
import com.training.carsharing.model.impl.Message;
import com.training.carsharing.model.impl.Message_;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Root;
import java.util.List;


@Repository
public class MessageDaoImpl extends AbstractDaoImpl<Message, Integer> implements IMessageDao {

    protected MessageDaoImpl() {
        super(Message.class);
    }

    @Override
    public Message createEntity() {
        final Message car = new Message();
        car.setVersion(Message.DEFAULT_VERSION);
        return car;
    }

    @Override
    public Message selectFullInfo(final Integer id) {
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
    public List<Message> selectAllFullInfo() {
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

//    @Override
//    public List<Book> getBooksByAuthors(List<Author> authors)
//    {
//        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
//        CriteriaQuery<Book> query = builder.createQuery(Book.class);
//
//        Root<Book> rootBook = query.from(Book.class);
//        ListJoin<Book, Author> joinBookAuthors = rootBook.join(Book_.authors);
//
//        Expression<List<Author>> bookAuthors = rootBook.get(Book_.authors);
//        Expression<Integer> countOfBookAuthors = builder.size(bookAuthors);
//        Expression<Long> countOfBooksInGroup = builder.count(rootBook);
//
//        Predicate predicateCountOfBookAuthorsEqualsInputListSize = builder.equal(countOfBookAuthors, authors.size());
//        Predicate predicateBookAuthorsInInputList = joinBookAuthors.in(authors);
//
//
//        query.where(builder.and(predicateCountOfBookAuthorsEqualsInputListSize, predicateBookAuthorsInInputList))
//                .groupBy(rootBook)
//                .having(builder.equal(countOfBooksInGroup, authors.size()));
//
//        return entityManager.createQuery(query).getResultList();
//    }


//    @Override
//    public long getCount(final MessageFilter filter) {
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
//    public List<Message> find(final MessageFilter filter) {
//        final EntityManager em = getEntityManager();
//        final CriteriaBuilder cb = em.getCriteriaBuilder();
//        final CriteriaQuery<Message> cq = cb.createQuery(Message.class);
//        final Root<model> from = cq.from(model.class);
//        cq.select(from);
//
//        if (filter.getSortColumn() != null) {
//            final SingularAttribute<? super model, ?> sortProperty = toMetamodelFormat(filter.getSortColumn());
//            final Path<?> expression = from.get(sortProperty);
//            cq.orderBy(new OrderImpl(expression, filter.getSortOrder()));
//        }
//
//        final TypedQuery<Message> q = em.createQuery(cq);
//        setPaging(filter, q);
//        return q.getResultList();
//    }
//
//    private SingularAttribute<? super model, ?> toMetamodelFormat(final String sortColumn) {
//        switch (sortColumn) {
//            case "created":
//                return Message_.created;
//            case "updated":
//                return Message_.updated;
//            case "id":
//                return Message_.id;
//            case "name":
//                return Message_.name;
//            default:
//                throw new UnsupportedOperationException("sorting is not supported by column:" + sortColumn);
//        }
//    }

}