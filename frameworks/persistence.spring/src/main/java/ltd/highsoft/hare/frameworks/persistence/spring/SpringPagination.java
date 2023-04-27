package ltd.highsoft.hare.frameworks.persistence.spring;

import ltd.highsoft.hare.frameworks.domain.core.Pagination;
import ltd.highsoft.hare.frameworks.domain.core.Sort;
import ltd.highsoft.hare.frameworks.domain.core.SortDirection;
import ltd.highsoft.hare.frameworks.domain.core.SortOrder;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;

import java.util.stream.Collectors;

import static org.springframework.data.domain.Sort.by;

public class SpringPagination implements Pagination {

    private final PageRequest impl;

    public static SpringPagination of(Pageable pageable) {
        return new SpringPagination(PageRequest.of(pageable.getPageNumber(), pageable.getPageSize(), pageable.getSort()));
    }

    public static SpringPagination of(Pagination other) {
        if (other instanceof SpringPagination) return (SpringPagination) other;
        return new SpringPagination(PageRequest.of(other.pageNumber(), other.pageSize(), toSpringSort(other.sort())));
    }

    @Override
    public int pageNumber() {
        return impl.getPageNumber();
    }

    @Override
    public int pageSize() {
        return impl.getPageSize();
    }

    @Override
    public Sort sort() {
        return SpringSort.of(impl.getSort());
    }

    @Override
    public Pagination withSort(Sort sort) {
        return SpringPagination.of(impl.withSort(toSpringSort(sort)));
    }

    @Override
    public Pagination withSort(SortDirection direction, String... properties) {
        return SpringPagination.of(impl.withSort(toSpringOrderDirection(direction), properties));
    }

    public Pageable pageable() {
        return impl;
    }

    protected SpringPagination(PageRequest pageable) {
        this.impl = pageable;
    }

    private static org.springframework.data.domain.Sort toSpringSort(Sort sort) {
        return by(sort.orders().stream().map(SpringPagination::toSpringOrder).collect(Collectors.toList()));
    }

    private static org.springframework.data.domain.Sort.Order toSpringOrder(SortOrder order) {
        return org.springframework.data.domain.Sort.Order.by(order.property()).with(toSpringOrderDirection(order.direction()));
    }

    private static Direction toSpringOrderDirection(SortDirection direction) {
        return direction.equals(SortDirection.ASC) ? Direction.ASC : Direction.DESC;
    }

}
