package ltd.highsoft.hare.frameworks.persistence.spring;

import ltd.highsoft.hare.frameworks.domain.core.SortDirection;
import org.junit.jupiter.api.Test;
import org.springframework.data.domain.Sort;

import static org.assertj.core.api.Assertions.assertThat;

class SpringSortOrderTest {

    @Test
    void should_be_able_to_carry_sort_field_name() {
        assertThat(SpringSortOrder.of(Sort.Order.asc("a")).property()).isEqualTo("a");
    }

    @Test
    void should_be_able_to_carry_sort_direction() {
        assertThat(SpringSortOrder.of(Sort.Order.desc("a")).direction()).isEqualTo(SortDirection.DESC);
        assertThat(SpringSortOrder.of(Sort.Order.asc("b")).direction()).isEqualTo(SortDirection.ASC);
    }

}
