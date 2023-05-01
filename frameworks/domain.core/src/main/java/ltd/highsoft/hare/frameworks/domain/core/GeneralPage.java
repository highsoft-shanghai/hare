package ltd.highsoft.hare.frameworks.domain.core;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

public class GeneralPage<T> implements Page<T> {

    private final List<T> content;
    private final Integer total;
    private final Pagination pagination;

    public GeneralPage(List<T> content, Integer total, Pagination pagination) {
        this.content = content;
        this.total = total;
        this.pagination = pagination;
    }

    public static <T> Page<T> from(List<T> content, Pagination pagination, Integer total) {
        return new GeneralPage<>(content, total, pagination);
    }

    @Override
    public List<T> getContent() {
        return content;
    }

    @Override
    public int getSize() {
        return pagination.pageSize();
    }

    @Override
    public int getNumber() {
        return pagination.pageNumber();
    }

    @Override
    public int getNumberOfElements() {
        return content.size();
    }

    @Override
    public int getNumberOfTotalPages() {
        return total / pagination.pageSize() + (total % pagination.pageSize() == 0 ? 0 : 1);
    }

    @Override
    public long getNumberOfTotalElements() {
        return total;
    }

    @Override
    public boolean getFirst() {
        return pagination.pageNumber() == 0;
    }

    @Override
    public boolean getLast() {
        return pagination.pageNumber() >= getNumberOfTotalPages() - 1;
    }

    @Override
    public <U> Page<U> map(Function<? super T, ? extends U> converter) {
        return GeneralPage.from(content.stream().map(converter).collect(Collectors.toList()), pagination, total);
    }

}
