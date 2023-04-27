package ltd.highsoft.hare.frameworks.persistence.jpa;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Order;
import jakarta.persistence.criteria.Root;
import ltd.highsoft.hare.frameworks.domain.core.*;
import ltd.highsoft.hare.frameworks.persistence.spring.SpringPage;
import ltd.highsoft.hare.frameworks.persistence.spring.SpringPagination;
import ltd.highsoft.hare.frameworks.persistence.spring.SpringSort;
import org.apache.commons.lang3.NotImplementedException;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.jpa.domain.Specification;

import javax.annotation.Nullable;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collectors;

import static java.util.Optional.ofNullable;
import static ltd.highsoft.hare.frameworks.persistence.jpa.CommonSelectionColumns.countOfRoot;
import static ltd.highsoft.hare.frameworks.persistence.jpa.CommonSelectionColumns.root;

public class JpaRepository<D, A> {

    private final EntityManager em;
    private final Class<D> dataClass;
    private final Function<A, D> asData;
    private final Function<D, A> asDomain;
    private final Function<D, Specification<D>> duplicateDetectPolicy;

    public JpaRepository(EntityManager em, Class<D> dataClass, Function<A, D> asData, Function<D, A> asDomain, Function<D, Specification<D>> duplicateDetectPolicy) {
        this.em = em;
        this.dataClass = dataClass;
        this.asData = asData;
        this.asDomain = asDomain;
        this.duplicateDetectPolicy = duplicateDetectPolicy;
    }

    public JpaRepository(EntityManager em, Class<D> dataClass, Function<A, D> asData, Function<D, A> asDomain) {
        this(em, dataClass, asData, asDomain, null);
    }

    public JpaRepository(EntityManager em, Class<D> dataClass, Function<D, A> asDomain) {
        this(em, dataClass, JpaRepository::notSupported, asDomain);
    }

    public void save(A aggregate) {
        D data = asData.apply(aggregate);
        detectDuplicate(data);
        em.merge(data);
    }

    public A load(Id id) {
        return loadOptional(id).orElseThrow(aggregateNotFound(id::asString));
    }

    public A load(Specification<D> specification) {
        return loadOptional(specification).orElseThrow(aggregateNotFound(() -> "(query)"));
    }

    public Optional<A> loadOptional(Id id) {
        return ofNullable(em.find(dataClass, id.asString())).map(asDomain);
    }

    public Optional<A> loadOptional(Specification<D> specification) {
        return ofNullable(queryFirst(createQuery(specification))).map(asDomain);
    }

    public List<A> list(Specification<D> specification) {
        return createQuery(specification).getResultStream().map(asDomain).collect(Collectors.toList());
    }

    public Page<A> list(Specification<D> specification, Pagination pagination) {
        TypedQuery<D> query = createQuery(root(), specification, pagination.sort(), dataClass);
        query.setFirstResult(pagination.pageNumber() * pagination.pageSize());
        query.setMaxResults(pagination.pageSize());
        return SpringPage.from(new PageImpl<>(query.getResultStream().map(asDomain).collect(Collectors.toList()), SpringPagination.of(pagination).pageable(), count(specification)));
    }

    public List<A> list(String jpql, Map<String, Object> parameters) {
        TypedQuery<D> query = em.createQuery(jpql, dataClass);
        parameters.forEach(query::setParameter);
        return query.getResultStream().map(asDomain).collect(Collectors.toList());
    }

    public Page<A> list(String jpql, String countJpql, Map<String, Object> parameters, Pagination pagination) {
        TypedQuery<D> query = em.createQuery(jpql, dataClass);
        parameters.forEach(query::setParameter);
        query.setFirstResult(pagination.pageNumber() * pagination.pageSize());
        query.setMaxResults(pagination.pageSize());
        return SpringPage.from(new PageImpl<>(query.getResultStream().map(asDomain).collect(Collectors.toList()), SpringPagination.of(pagination).pageable(), count(countJpql, parameters)));
    }

    public long count(Specification<D> specification) {
        return createQuery(countOfRoot(), specification, SpringSort.UNSORTED, Long.class).getSingleResult();
    }

    public long count(String countJpql, Map<String, Object> parameters) {
        TypedQuery<Long> query = em.createQuery(countJpql, Long.class);
        parameters.forEach(query::setParameter);
        return query.getSingleResult();
    }

    public <X extends Number> X sumOn(String attributeName, Specification<D> specification, Class<X> resultType, X defaultValue) {
        return ofNullable(createQuery(CommonSelectionColumns.sumOn(attributeName), specification, SpringSort.UNSORTED, resultType).getSingleResult()).orElse(defaultValue);
    }

    public void delete(Id id) {
        em.remove(em.find(dataClass, id.asString()));
    }

    public void deleteAll(Specification<D> specification) {
        createQuery(specification).getResultStream().forEach(em::remove);
    }

    public boolean exists(Id id) {
        return this.count(Specifications.withId(id)) > 0;
    }

    @Nullable
    public <X> X aggregate(SelectionColumns<D, X> columns, Specification<D> specification, Class<X> resultType) {
        return createQuery(columns, specification, SpringSort.UNSORTED, resultType).getSingleResult();
    }

    private static <TD, TA> TD notSupported(TA x) {
        throw new NotImplementedException("Put aggregates into this repository is not supported");
    }

    private void detectDuplicate(D data) {
        if (duplicateDetectPolicy == null) return;
        Specification<D> specification = duplicateDetectPolicy.apply(data);
        if (count(specification) > 0) {
            throw new DuplicateAggregateDetectedException();
        }
    }

    private TypedQuery<D> createQuery(Specification<D> specification) {
        return createQuery(root(), specification, SpringSort.UNSORTED, dataClass);
    }

    private <X> TypedQuery<X> createQuery(SelectionColumns<D, X> columns, Specification<D> specification, Sort sort, Class<X> resultType) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<X> query = cb.createQuery(resultType);
        Root<D> root = query.from(dataClass);
        query.select(columns.toSelection(root, query, cb)).where(specification.toPredicate(root, query, cb)).orderBy(toOrders(sort, cb, root));
        return em.createQuery(query);
    }

    private List<Order> toOrders(Sort sort, CriteriaBuilder cb, Root<D> root) {
        return sort.orders().stream().map(x -> toOrder(x, cb, root)).collect(Collectors.toList());
    }

    private Order toOrder(SortOrder source, CriteriaBuilder cb, Root<D> root) {
        return SortDirection.ASC.equals(source.direction()) ? cb.asc(root.get(source.property())) : cb.desc(root.get(source.property()));
    }

    private D queryFirst(TypedQuery<D> query) {
        List<D> list = query.setMaxResults(1).getResultList();
        return list.isEmpty() ? null : list.get(0);
    }

    private Supplier<AggregateNotFoundException> aggregateNotFound(Supplier<String> messageSupplier) {
        return () -> new AggregateNotFoundException(dataClass.getSimpleName(), messageSupplier.get());
    }

}
