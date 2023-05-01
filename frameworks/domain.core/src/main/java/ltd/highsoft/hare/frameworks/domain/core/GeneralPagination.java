package ltd.highsoft.hare.frameworks.domain.core;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class GeneralPagination implements Pagination {

    private final int pageNumber;
    private final int pageSize;

    public static GeneralPagination of(int pageNumber, int pageSize) {
        return new GeneralPagination(pageNumber, pageSize);
    }

    @Override
    public int pageNumber() {
        return pageNumber;
    }

    @Override
    public int pageSize() {
        return pageSize;
    }

    @Override
    public int limit() {
        return pageSize;
    }

    @Override
    public int offset() {
        return pageSize * pageNumber;
    }

}
