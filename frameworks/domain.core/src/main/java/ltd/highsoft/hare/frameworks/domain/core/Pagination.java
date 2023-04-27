package ltd.highsoft.hare.frameworks.domain.core;

public interface Pagination {

    int pageNumber();

    int pageSize();

    Sort sort();

    Pagination withSort(Sort sort);

    Pagination withSort(SortDirection direction, String... properties);

}
