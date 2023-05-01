package ltd.highsoft.hare.frameworks.domain.core;

public interface Pagination {

    int pageNumber();

    int pageSize();

    int limit();

    int offset();

}
