package ltd.highsoft.hare.frameworks.persistence.spring;

import org.junit.jupiter.api.Test;
import org.springframework.data.domain.Sort;

import static org.assertj.core.api.Assertions.assertThat;

class SpringSortTest {

    @Test
    void should_be_able_to_carry_sort_orders() {
        var sort = SpringSort.of(Sort.by(Sort.Order.desc("a"), Sort.Order.asc("b")));
        assertThat(sort.orders()).containsExactly(SpringSortOrder.of(Sort.Order.desc("a")), SpringSortOrder.of(Sort.Order.asc("b")));
    }

}
