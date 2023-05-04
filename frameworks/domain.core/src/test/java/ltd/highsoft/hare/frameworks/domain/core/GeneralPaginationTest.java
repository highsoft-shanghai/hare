package ltd.highsoft.hare.frameworks.domain.core;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

class GeneralPaginationTest {

    @Test
    void should_carry_page_number() {
        var pagination = GeneralPagination.of(3, 10);
        Assertions.assertThat(pagination.pageNumber()).isEqualTo(3);
        Assertions.assertThat(pagination.pageSize()).isEqualTo(10);
        Assertions.assertThat(pagination.limit()).isEqualTo(10);
        Assertions.assertThat(pagination.offset()).isEqualTo(30);
    }

}
