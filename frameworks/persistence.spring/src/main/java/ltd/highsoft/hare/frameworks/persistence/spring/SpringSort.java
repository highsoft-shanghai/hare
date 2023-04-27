package ltd.highsoft.hare.frameworks.persistence.spring;

import lombok.EqualsAndHashCode;
import lombok.ToString;
import ltd.highsoft.hare.frameworks.domain.core.Sort;
import ltd.highsoft.hare.frameworks.domain.core.SortOrder;

import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@ToString
@EqualsAndHashCode
public class SpringSort implements Sort {

    public static final Sort UNSORTED = SpringSort.of(org.springframework.data.domain.Sort.unsorted());
    private final org.springframework.data.domain.Sort impl;

    public static Sort of(org.springframework.data.domain.Sort impl) {
        return new SpringSort(impl);
    }

    @Override
    public Iterator<SortOrder> iterator() {
        return SpringSortOrderIterator.of(impl.iterator());
    }

    @Override
    public List<SortOrder> orders() {
        return StreamSupport.stream(spliterator(), false).collect(Collectors.toList());
    }

    protected SpringSort(org.springframework.data.domain.Sort impl) {
        this.impl = impl;
    }

}
