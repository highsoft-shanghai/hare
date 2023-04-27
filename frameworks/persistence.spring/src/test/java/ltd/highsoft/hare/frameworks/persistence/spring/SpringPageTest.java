package ltd.highsoft.hare.frameworks.persistence.spring;

import org.junit.jupiter.api.Test;
import org.springframework.data.domain.*;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class SpringPageTest {

    @Test
    void should_be_able_to_carry_content() {
        var page = SpringPage.from(new PageImpl<>(List.of("a", "b"), Pageable.ofSize(5).next().next(), 30));
        assertThat(page.content()).containsExactly("a", "b");
    }

    @Test
    void should_be_able_to_carry_number_of_elements() {
        var page = SpringPage.from(new PageImpl<>(List.of("a", "b"), Pageable.ofSize(5).next().next(), 30));
        assertThat(page.numberOfElements()).isEqualTo(2);
    }

    @Test
    void should_be_able_to_carry_page_number() {
        var page = SpringPage.from(new PageImpl<>(List.of("a", "b"), Pageable.ofSize(5).next().next(), 30));
        assertThat(page.number()).isEqualTo(2);
    }

    @Test
    void should_be_able_to_carry_page_size() {
        var page = SpringPage.from(new PageImpl<>(List.of("a", "b"), Pageable.ofSize(5).next().next(), 30));
        assertThat(page.size()).isEqualTo(5);
    }

    @Test
    void should_be_able_to_carry_total_number_of_elements() {
        var page = SpringPage.from(new PageImpl<>(List.of("a", "b"), Pageable.ofSize(5).next().next(), 30));
        assertThat(page.numberOfTotalElements()).isEqualTo(30);
    }

    @Test
    void should_be_able_to_carry_total_number_of_pages() {
        var page = SpringPage.from(new PageImpl<>(List.of("a", "b"), Pageable.ofSize(5).next().next(), 30));
        assertThat(page.numberOfTotalPages()).isEqualTo(6);
    }

    @Test
    void should_be_able_to_indicate_first_page_reached() {
        assertThat(SpringPage.from(new PageImpl<>(List.of("a", "b"), Pageable.ofSize(5), 30)).first()).isTrue();
        assertThat(SpringPage.from(new PageImpl<>(List.of("a", "b"), Pageable.ofSize(5).next(), 30)).first()).isFalse();
    }

    @Test
    void should_be_able_to_indicate_last_page_reached() {
        assertThat(SpringPage.from(new PageImpl<>(List.of("a", "b"), Pageable.ofSize(2).next(), 2)).last()).isTrue();
        assertThat(SpringPage.from(new PageImpl<>(List.of("a", "b"), Pageable.ofSize(2), 4)).last()).isFalse();
    }

    @Test
    void should_be_able_to_map_to_others() {
        var page = SpringPage.from(new PageImpl<>(List.of("a", "b"), Pageable.ofSize(5).next().next(), 30));
        assertThat(page.map(x -> x + x).content()).containsExactly("aa", "bb");
    }

    @Test
    void should_be_able_to_carry_sort() {
        var page = SpringPage.from(new PageImpl<>(List.of("b", "a"), PageRequest.of(0, 2, Sort.by(Sort.Order.desc("f1"), Sort.Order.asc("f2"))), 30));
        assertThat(page.sort()).isEqualTo(SpringSort.of(Sort.by(Sort.Order.desc("f1"), Sort.Order.asc("f2"))));
    }

}
