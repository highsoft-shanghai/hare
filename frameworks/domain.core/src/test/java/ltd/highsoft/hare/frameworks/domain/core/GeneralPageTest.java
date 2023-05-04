package ltd.highsoft.hare.frameworks.domain.core;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

class GeneralPageTest {

    @Test
    void should_carry_content() {
        var page = GeneralPage.from(List.of("a", "b"), GeneralPagination.of(1, 2), 30);
        Assertions.assertThat(page.getContent()).containsExactly("a", "b");
    }

    @Test
    void should_carry_number_of_elements() {
        var page = GeneralPage.from(List.of("a", "b"), GeneralPagination.of(1, 2), 30);
        Assertions.assertThat(page.getNumberOfElements()).isEqualTo(2);
    }

    @Test
    void should_carry_page_number() {
        var page = GeneralPage.from(List.of("a", "b"), GeneralPagination.of(1, 2), 30);
        Assertions.assertThat(page.getNumber()).isEqualTo(1);
    }

    @Test
    void should_carry_page_size() {
        var page = GeneralPage.from(List.of("a", "b"), GeneralPagination.of(1, 2), 30);
        Assertions.assertThat(page.getSize()).isEqualTo(2);
    }

    @Test
    void should_carry_total_number_of_elements() {
        var page = GeneralPage.from(List.of("a", "b"), GeneralPagination.of(1, 2), 30);
        Assertions.assertThat(page.getNumberOfTotalElements()).isEqualTo(30);
    }

    @Test
    void should_carry_total_number_of_pages() {
        var page = GeneralPage.from(List.of("a", "b"), GeneralPagination.of(1, 2), 30);
        Assertions.assertThat(page.getNumberOfTotalPages()).isEqualTo(15);
        var page2 = GeneralPage.from(List.of("a", "b"), GeneralPagination.of(1, 7), 30);
        Assertions.assertThat(page2.getNumberOfTotalPages()).isEqualTo(5);
    }

    @Test
    void should_indicate_first_page_reached() {
        Assertions.assertThat(GeneralPage.from(List.of("a", "b"), GeneralPagination.of(0, 2), 30).getFirst()).isTrue();
        Assertions.assertThat(GeneralPage.from(List.of("a", "b"), GeneralPagination.of(1, 2), 30).getFirst()).isFalse();
    }

    @Test
    void should_indicate_last_page_reached() {
        Assertions.assertThat(GeneralPage.from(List.of("a", "b"), GeneralPagination.of(14, 2), 30).getLast()).isTrue();
        Assertions.assertThat(GeneralPage.from(List.of("a", "b"), GeneralPagination.of(1, 2), 30).getLast()).isFalse();
    }

    @Test
    void should_convert_to_expect() {
        var page = GeneralPage.from(List.of("a", "b"), GeneralPagination.of(1, 2), 30);
        Assertions.assertThat(page.map(x -> x + x).getContent()).containsExactly("aa", "bb");
    }
}
