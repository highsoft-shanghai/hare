package ltd.highsoft.hare.foundations.iam.gateways.persistence;

import jakarta.persistence.EntityManager;
import ltd.highsoft.hare.frameworks.domain.core.*;
import ltd.highsoft.hare.frameworks.persistence.jpa.JpaRepository;
import ltd.highsoft.hare.frameworks.persistence.spring.SpringPagination;
import ltd.highsoft.hare.foundations.iam.domain.Role;
import ltd.highsoft.hare.foundations.iam.domain.Roles;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static ltd.highsoft.hare.frameworks.persistence.jpa.SpecificationUtils.fullLike;
import static ltd.highsoft.hare.frameworks.persistence.jpa.Specifications.inTenant;
import static org.springframework.data.jpa.domain.Specification.where;

@Component
public class JpaRoles implements Roles {

    private static final int LIMIT = 1000;

    private final JpaRepository<JpaRole, Role> repository;

    public JpaRoles(EntityManager entityManager) {
        this.repository = new JpaRepository<>(entityManager, JpaRole.class, JpaRole::new, JpaRole::asDomain);
    }

    @Override
    @Transactional(readOnly = true)
    public Role get(Id id) {
        return repository.load(id);
    }

    @Override
    @Transactional(readOnly = true)
    public Role get(ScopedId id) {
        return repository.load(idEquals(id));
    }

    @Override
    @Transactional
    public void add(Role role) {
        repository.save(role);
    }

    @Override
    @Transactional
    public void remove(ScopedId id) {
        repository.deleteAll(idEquals(id));
    }

    @Override
    public Page<Role> search(Id tenantId, String q, Pagination pagination) {
        return repository.list(where(nameContains(q).or(remarksContains(q))).and(inTenant(tenantId)),
            pagination.withSort(SortDirection.ASC, "name"));
    }

    @Override
    public List<Role> search(Id tenantId, String q) {
        return this.repository.list(
            where((nameContains(q))).and(inTenant(tenantId)),
            SpringPagination.of(PageRequest.of(0, LIMIT, org.springframework.data.domain.Sort.Direction.ASC, "name"))
        ).content();
    }

    private Specification<JpaRole> nameContains(String q) {
        return (root, query, builder) -> builder.like(root.get("name"), fullLike(q));
    }

    private Specification<JpaRole> remarksContains(String q) {
        return (root, query, builder) -> builder.like(root.get("remarks"), fullLike(q));
    }

    private Specification<JpaRole> idEquals(ScopedId id) {
        return (root, query, builder) -> builder.and(builder.equal(root.get("tenantId"), id.tenantId().asString()), builder.equal(root.get("id"), id.id().asString()));
    }

}
