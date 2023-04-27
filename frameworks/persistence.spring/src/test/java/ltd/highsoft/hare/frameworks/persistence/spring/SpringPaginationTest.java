package ltd.highsoft.hare.frameworks.persistence.spring;

import ltd.highsoft.hare.frameworks.domain.core.Pagination;
import ltd.highsoft.hare.frameworks.domain.core.SortDirection;
import ltd.highsoft.hare.frameworks.domain.core.SortOrder;
import org.junit.jupiter.api.Test;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;

class SpringPaginationTest {

    @Test
    void should_be_able_to_carry_page_number() {
        var pagination = SpringPagination.of(PageRequest.of(3, 10));
        assertThat(pagination.pageNumber()).isEqualTo(3);
        assertThat(pagination.pageable()).isEqualTo(PageRequest.of(3, 10));
    }

    @Test
    void should_be_able_to_carry_page_size() {
        var pagination = SpringPagination.of(PageRequest.of(3, 10));
        assertThat(pagination.pageSize()).isEqualTo(10);
    }

    @Test
    void should_be_able_to_carry_sort() {
        var pagination = SpringPagination.of(PageRequest.of(3, 10, Sort.by(Sort.Order.desc("a"), Sort.Order.asc("b"))));
        assertThat(pagination.sort()).isEqualTo(SpringSort.of(Sort.by(Sort.Order.desc("a"), Sort.Order.asc("b"))));
    }

    @Test
    void should_be_able_to_fill_sort_after_creation() {
        Pagination pagination = SpringPagination.of(PageRequest.of(3, 10)).withSort(SpringSort.of(Sort.by(Sort.Order.desc("a"), Sort.Order.asc("b"))));
        assertThat(pagination.sort()).isEqualTo(SpringSort.of(Sort.by(Sort.Order.desc("a"), Sort.Order.asc("b"))));
    }

    @Test
    void should_be_able_to_fill_sort_with_direction_and_properties_after_creation() {
        Pagination pagination = SpringPagination.of(PageRequest.of(3, 10)).withSort(SortDirection.DESC, "b");
        assertThat(pagination.sort()).isEqualTo(SpringSort.of(Sort.by(Sort.Order.desc("b"))));
    }

    @Test
    void should_reuse_others_when_other_is_spring_pagination() {
        SpringPagination source = SpringPagination.of(PageRequest.of(3, 10, Sort.Direction.ASC, "a", "b"));
        SpringPagination pagination = SpringPagination.of(source);
        assertThat(pagination).isEqualTo(source);
    }

    @Test
    void should_be_able_to_create_from_other_pagination() {
        var sort = mock(ltd.highsoft.hare.frameworks.domain.core.Sort.class);
        Pagination other = mock(Pagination.class);
        var order1 = mock(SortOrder.class);
        var order2 = mock(SortOrder.class);
        given(other.pageSize()).willReturn(10);
        given(other.pageNumber()).willReturn(3);
        given(other.sort()).willReturn(sort);
        given(sort.orders()).willReturn(List.of(order1, order2));
        given(order1.direction()).willReturn(SortDirection.ASC);
        given(order1.property()).willReturn("a");
        given(order2.direction()).willReturn(SortDirection.DESC);
        given(order2.property()).willReturn("b");
        SpringPagination pagination = SpringPagination.of(other);
        assertThat(pagination.pageNumber()).isEqualTo(3);
        assertThat(pagination.pageSize()).isEqualTo(10);
        assertThat(pagination.sort()).isEqualTo(SpringSort.of(Sort.by(Sort.Order.by("a").with(Sort.Direction.ASC), Sort.Order.by("b").with(Sort.Direction.DESC))));
    }

}
