package ltd.highsoft.hare.frameworks.persistence.jpa;

import lombok.*;
import ltd.highsoft.hare.frameworks.domain.core.Id;
import org.springframework.data.jpa.domain.Specification;

import static org.springframework.data.jpa.domain.Specification.not;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class Specifications {

    public static <T> Specification<T> inTenant(Id tenantId) {
        return (root, query, builder) -> builder.equal(root.get("tenantId"), tenantId.asString());
    }

    public static <T> Specification<T> inOrder(Id productionOrderId) {
        return (root, query, builder) -> builder.equal(root.get("productionOrderId"), productionOrderId.asString());
    }

    public static <T> Specification<T> inTagGroup(String tagGroupId) {
        return (root, query, builder) -> builder.equal(root.get("groupId"), tagGroupId);
    }

    public static <T> Specification<T> withId(Id id) {
        return (root, query, builder) -> builder.equal(root.get("id"), id.asString());
    }

    public static <T> Specification<T> withId(String id) {
        return (root, query, builder) -> builder.equal(root.get("id"), id);
    }

    public static <T> Specification<T> duplicateByCodeInTenant(String code, String id, String tenantId) {
        return Specifications.<T>codeEquals(code).and(inTenant(Id.id(tenantId))).and(not(withId(id)));
    }

    public static <T> Specification<T> duplicateByCodeInTenantAndNotArchived(String code, String id, String tenantId, String state) {
        return Specifications.<T>codeEquals(code).and(inTenant(Id.id(tenantId))).and(not(withId(id))).and(notArchived(state));
    }

    public static <T> Specification<T> duplicateByCodeInTagGroup(String code, String id, String tagGroupId) {
        return Specifications.<T>codeEquals(code).and(inTagGroup(tagGroupId)).and(not(withId(id)));
    }

    public static <T> Specification<T> duplicateByCodeInOrder(String code, String id, String productionOrderId) {
        return Specifications.<T>codeEquals(code).and(inOrder(Id.id(productionOrderId))).and(not(withId(id)));
    }

    public static <T> Specification<T> duplicateByProductInTenant(String productId, String id, String teamId) {
        return Specifications.<T>productEquals(productId).and(inTeam(teamId)).and(not(withId(id)));
    }

    public static <T> Specification<T> codeEquals(String code) {
        return (root, query, builder) -> builder.equal(root.get("code"), code);
    }

    public static <T> Specification<T> inTeam(String teamId) {
        return (root, query, builder) -> builder.equal(root.get("teamId"), teamId);
    }

    public static <T> Specification<T> productEquals(String productId) {
        return (root, query, builder) -> builder.equal(root.get("productId"), productId);
    }

    public static <T> Specification<T> all() {
        return ((root, query, builder) -> builder.and());
    }

    private static <T> Specification<T> notArchived(String state) {
        return (root, query, builder) -> builder.notEqual(root.get("stateId"), state);
    }

}
