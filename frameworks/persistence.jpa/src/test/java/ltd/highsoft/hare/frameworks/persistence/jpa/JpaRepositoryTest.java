package ltd.highsoft.hare.frameworks.persistence.jpa;

import jakarta.annotation.Resource;
import jakarta.persistence.EntityManager;
import ltd.highsoft.hare.frameworks.domain.core.AggregateNotFoundException;
import ltd.highsoft.hare.frameworks.domain.core.DuplicateAggregateDetectedException;
import ltd.highsoft.hare.frameworks.domain.core.Id;
import ltd.highsoft.hare.frameworks.domain.core.Page;
import ltd.highsoft.hare.frameworks.persistence.spring.SpringPagination;
import ltd.highsoft.hare.frameworks.test.container.WithTestContainers;
import ltd.highsoft.hare.frameworks.test.postgres.PostgresContainer;
import org.apache.commons.lang3.NotImplementedException;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.transaction.annotation.Transactional;

import java.util.Map;

import static ltd.highsoft.hare.frameworks.domain.core.Id.id;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;
import static org.springframework.data.domain.Sort.Direction.ASC;
import static org.springframework.data.domain.Sort.Direction.DESC;

@Transactional
@SpringBootTest
@WithTestContainers(containers = PostgresContainer.class)
class JpaRepositoryTest {

    private @Resource EntityManager em;
    private JpaRepository<JpaTestEntity, TestEntity> repository;

    @BeforeEach
    void setUp() {
        repository = new JpaRepository<>(em, JpaTestEntity.class, JpaTestEntity::new, JpaTestEntity::asDomain);
    }

    @Test
    void should_be_able_to_save_entities() {
        repository.save(new TestEntity(id("to-save"), "To save"));
        assertThat(em.find(JpaTestEntity.class, "to-save").asDomain()).isEqualTo(new TestEntity(id("to-save"), "To save"));
    }

    @Test
    void should_be_able_to_load_entities() {
        em.persist(new JpaTestEntity(new TestEntity(id("saved"), "Saved")));
        assertThat(repository.load(id("saved"))).isEqualTo(new TestEntity(id("saved"), "Saved"));
    }

    @Test
    void should_be_able_to_load_optional_entities() {
        em.persist(new JpaTestEntity(new TestEntity(id("saved"), "Saved")));
        Assertions.assertThat(repository.loadOptional(id("saved"))).hasValue(new TestEntity(id("saved"), "Saved"));
        Assertions.assertThat(repository.loadOptional(id("not-found"))).isEmpty();
    }

    @Test
    void should_be_able_to_load_optional_entities_by_criteria() {
        em.persist(new JpaTestEntity(new TestEntity(id("saved"), "Saved")));
        Assertions.assertThat(repository.loadOptional((r, q, b) -> b.equal(r.get("id"), "saved"))).hasValue(new TestEntity(id("saved"), "Saved"));
        Assertions.assertThat(repository.loadOptional((r, q, b) -> b.equal(r.get("id"), "not-found"))).isEmpty();
    }

    @Test
    void should_fire_aggregate_not_found_when_try_to_load_not_existed_entities() {
        Throwable throwable = catchThrowable(() -> repository.load(id("not-existed")));
        assertThat(throwable).isInstanceOf(AggregateNotFoundException.class);
        assertThat(throwable).hasMessage("error.aggregate-not-found: [JpaTestEntity, not-existed]");
    }

    @Test
    void should_be_able_load_single_entity_by_criteria() {
        em.persist(new JpaTestEntity(new TestEntity(id("saved"), "Saved")));
        assertThat(repository.load((root, query, builder) -> builder.equal(root.get("name"), "Saved"))).isEqualTo(new TestEntity(id("saved"), "Saved"));
    }

    @Test
    void should_fire_aggregate_not_found_when_try_to_load_not_existed_entities_by_criteria() {
        Throwable throwable = catchThrowable(() -> repository.load((root, query, builder) -> builder.equal(root.get("name"), "Saved")));
        assertThat(throwable).isInstanceOf(AggregateNotFoundException.class);
        assertThat(throwable).hasMessage("error.aggregate-not-found: [JpaTestEntity, (query)]");
    }

    @Test
    void should_be_able_to_list_entities_by_criteria() {
        TestEntity entity1 = new TestEntity(id("saved1"), "Saved");
        TestEntity entity2 = new TestEntity(id("saved2"), "Saved");
        em.persist(new JpaTestEntity(entity1));
        em.persist(new JpaTestEntity(entity2));
        Assertions.assertThat(repository.list((root, query, builder) -> builder.equal(root.get("name"), "Saved"))).containsExactlyInAnyOrder(entity1, entity2);
    }

    @Test
    void should_be_able_to_list_page_of_entities_by_criteria() {
        TestEntity entity1 = new TestEntity(id("saved1"), "Saved");
        TestEntity entity2 = new TestEntity(id("saved2"), "Saved");
        em.persist(new JpaTestEntity(entity1));
        em.persist(new JpaTestEntity(entity2));
        Specification<JpaTestEntity> specification = (root, query, builder) -> builder.equal(root.get("name"), "Saved");
        Page<TestEntity> page = repository.list(specification, SpringPagination.of(PageRequest.of(0, 1, ASC, "id")));
        Assertions.assertThat(page.content()).containsExactlyInAnyOrder(entity1);
        assertThat(page.numberOfTotalElements()).isEqualTo(2);
        assertThat(page.numberOfElements()).isEqualTo(1);
        assertThat(page.numberOfTotalPages()).isEqualTo(2);
    }

    @Test
    void should_be_able_to_list_page_of_entities_by_criteria_with_order() {
        TestEntity entity1 = new TestEntity(id("saved1"), "Saved");
        TestEntity entity2 = new TestEntity(id("saved2"), "Saved");
        em.persist(new JpaTestEntity(entity1));
        em.persist(new JpaTestEntity(entity2));
        Specification<JpaTestEntity> specification = (root, query, builder) -> builder.equal(root.get("name"), "Saved");
        Page<TestEntity> page = repository.list(specification, SpringPagination.of(PageRequest.of(0, 1, DESC, "id")));
        Assertions.assertThat(page.content()).containsExactlyInAnyOrder(entity2);
        assertThat(page.numberOfTotalElements()).isEqualTo(2);
        assertThat(page.numberOfElements()).isEqualTo(1);
        assertThat(page.numberOfTotalPages()).isEqualTo(2);
    }

    @Test
    void should_be_able_to_count_entities() {
        em.persist(new JpaTestEntity(new TestEntity(id("saved1"), "Saved")));
        em.persist(new JpaTestEntity(new TestEntity(id("saved2"), "Saved")));
        assertThat(repository.count((root, query, builder) -> builder.equal(root.get("name"), "Saved"))).isEqualTo(2);
    }

    @Test
    void should_be_able_to_delete_entities_by_id() {
        em.persist(new JpaTestEntity(new TestEntity(id("saved"), "Saved")));
        repository.delete(id("saved"));
        assertThat(em.find(JpaTestEntity.class, "saved")).isNull();
    }

    @Test
    void should_be_able_to_delete_entities_by_query() {
        em.persist(new JpaTestEntity(new TestEntity(id("saved1"), "Saved")));
        em.persist(new JpaTestEntity(new TestEntity(id("saved2"), "Saved")));
        repository.deleteAll((root, query, builder) -> builder.equal(root.get("name"), "Saved"));
        assertThat(em.find(JpaTestEntity.class, "saved1")).isNull();
        assertThat(em.find(JpaTestEntity.class, "saved2")).isNull();
    }

    @Test
    void should_be_able_to_support_readonly_repositories() {
        repository = new JpaRepository<>(em, JpaTestEntity.class, JpaTestEntity::asDomain);
        Throwable throwable = catchThrowable(() -> repository.save(new TestEntity(id("to-save"), "To save")));
        assertThat(throwable).isInstanceOf(NotImplementedException.class);
        assertThat(throwable).hasMessage("Put aggregates into this repository is not supported");
    }

    @Test
    void should_tell_entity_exist_if_there_is_one_entity_with_same_id() {
        em.persist(new JpaTestEntity(new TestEntity(id("same_id"), "same_id")));
        assertThat(repository.exists(Id.id("same_id"))).isTrue();
    }

    @Test
    void should_tell_entity_not_exist_if_no_entity_with_same_id() {
        assertThat(repository.exists(Id.id("not-found"))).isFalse();
    }

    @Test
    void should_be_able_to_sum_entity_properties() {
        em.persist(new JpaTestEntity(new TestEntity(id("saved1"), "Saved")));
        em.persist(new JpaTestEntity(new TestEntity(id("saved2"), "Saved")));
        assertThat(repository.sumOn("quantity", (root, query, builder) -> builder.equal(root.get("quantity"), 1), Integer.class, 0)).isEqualTo(2);
    }

    @Test
    void should_be_able_to_sum_empty_tables() {
        assertThat(repository.sumOn("quantity", (root, query, builder) -> builder.equal(root.get("quantity"), 1), Integer.class, 0)).isEqualTo(0);
    }

    @Test
    void should_be_able_to_aggregate_entities() {
        em.persist(new JpaTestEntity(new TestEntity(id("saved1"), "Saved")));
        em.persist(new JpaTestEntity(new TestEntity(id("saved2"), "Saved")));
        assertThat(repository.aggregate((root, query, builder) -> builder.sum(root.get("quantity")), Specifications.all(), Integer.class)).isEqualTo(2L);
    }

    @Test
    void should_reject_duplicate_entities_while_putting() {
        em.persist(new JpaTestEntity(new TestEntity(id("saved1"), "Saved")));
        repository = new JpaRepository<>(em, JpaTestEntity.class, JpaTestEntity::new, JpaTestEntity::asDomain, entity -> (root, query, builder) -> builder.equal(root.get("name"), entity.name()));
        Throwable throwable = catchThrowable(() -> repository.save(new TestEntity(id("to-save"), "Saved")));
        assertThat(throwable).isInstanceOf(DuplicateAggregateDetectedException.class);
        assertThat(throwable).hasMessage("error.duplicate-aggregate-detected");
    }

    @Test
    void should_be_able_to_list_entities_by_jpql() {
        TestEntity entity = new TestEntity(id("saved2"), "saved3", 20);
        em.persist(new JpaTestEntity(new TestEntity(id("saved1"), "saved2", 10)));
        em.persist(new JpaTestEntity(entity));
        em.persist(new JpaTestEntity(new TestEntity(id("saved3"), null, 30)));
        Assertions.assertThat(repository.list("select t from test_entities t left join test_entities tt on t.name = tt.id where tt.quantity = :quantity", Map.of("quantity", 30))).containsExactlyInAnyOrder(entity);
    }

    @Test
    void should_be_able_to_list_page_of_entities_by_jpql() {
        em.persist(new JpaTestEntity(new TestEntity(id("saved1"), "Saved 1")));
        em.persist(new JpaTestEntity(new TestEntity(id("saved2"), "Saved 2")));
        Page<TestEntity> page = repository.list("select t from test_entities t where t.name like :name", "select count(t) from test_entities t where t.name like :name", Map.of("name", "Saved %"), SpringPagination.of(PageRequest.of(0, 1, ASC, "name")));
        Assertions.assertThat(page.content()).containsExactlyInAnyOrder(new TestEntity(id("saved1"), "Saved 1"));
        assertThat(page.numberOfTotalElements()).isEqualTo(2);
        assertThat(page.numberOfElements()).isEqualTo(1);
        assertThat(page.numberOfTotalPages()).isEqualTo(2);
    }

}
