package com.training.carsharing.dao.impl;

import com.training.carsharing.dao.ICarDao;
import com.training.carsharing.model.ICar;
import com.training.carsharing.model.impl.Car;
import com.training.carsharing.model.impl.Car_;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceException;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Root;
import java.util.List;


@Repository
public class CarDaoImpl extends AbstractDaoImpl<ICar, Integer> implements ICarDao {

 protected CarDaoImpl() { super(Car.class); }

    @Override
    public ICar createEntity() {
        final ICar car = new Car();
        car.setVersion(ICar.DEFAULT_VERSION);
        return car;
    }

    @Override
    public ICar selectFullInfo(final Integer id) {
        final EntityManager em = getEntityManager();
        final CriteriaBuilder cb = em.getCriteriaBuilder();
        final CriteriaQuery<ICar> cq = cb.createQuery(ICar.class);
        final Root<Car> from = cq.from(Car.class);
        cq.select(from);

        from.fetch(Car_.model, JoinType.LEFT);
//        from.fetch(Car_.ad, JoinType.LEFT);
//        from.fetch(Car_.parameters, JoinType.LEFT);

        cq.where(cb.equal(from.get(Car_.id),id));

        final List <ICar> resultList = em.createQuery(cq).getResultList();
        return resultList.isEmpty() ? null : resultList.get(0);
    }

    @Override
    public List<ICar> selectAllFullInfo() {
        final EntityManager em = getEntityManager();
        final CriteriaBuilder cb = em.getCriteriaBuilder();
        final CriteriaQuery<ICar> cq = cb.createQuery(ICar.class);
        final Root<Car> from = cq.from(Car.class);
        cq.select(from);

        from.fetch(Car_.model, JoinType.LEFT);
//        from.fetch(Car_.ad, JoinType.LEFT);
//        from.fetch(Car_.parameters, JoinType.LEFT);

        final TypedQuery<ICar> q = em.createQuery(cq);
        List<ICar> resultList = q.getResultList();
//        Set<ICar> targetSet = new HashSet<ICar>(resultList);
//        resultList = targetSet.stream().collect(Collectors.toList());;
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
//    public long getCount(final CarFilter filter) {
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
//    public List<ICar> find(final CarFilter filter) {
//        final EntityManager em = getEntityManager();
//        final CriteriaBuilder cb = em.getCriteriaBuilder();
//        final CriteriaQuery<ICar> cq = cb.createQuery(ICar.class);
//        final Root<model> from = cq.from(model.class);
//        cq.select(from);
//
//        if (filter.getSortColumn() != null) {
//            final SingularAttribute<? super model, ?> sortProperty = toMetamodelFormat(filter.getSortColumn());
//            final Path<?> expression = from.get(sortProperty);
//            cq.orderBy(new OrderImpl(expression, filter.getSortOrder()));
//        }
//
//        final TypedQuery<ICar> q = em.createQuery(cq);
//        setPaging(filter, q);
//        return q.getResultList();
//    }
//
//    private SingularAttribute<? super model, ?> toMetamodelFormat(final String sortColumn) {
//        switch (sortColumn) {
//            case "created":
//                return Car_.created;
//            case "updated":
//                return Car_.updated;
//            case "id":
//                return Car_.id;
//            case "name":
//                return Car_.name;
//            default:
//                throw new UnsupportedOperationException("sorting is not supported by column:" + sortColumn);
//        }
//    }

}