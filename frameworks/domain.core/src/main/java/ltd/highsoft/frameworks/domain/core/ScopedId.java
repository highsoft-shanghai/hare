package ltd.highsoft.frameworks.domain.core;

import lombok.*;
import org.apache.commons.lang3.StringUtils;

@ToString
@EqualsAndHashCode
public final class ScopedId {

    public static final ScopedId EMPTY = id(StringUtils.EMPTY, StringUtils.EMPTY);
    private final Id id;
    private final Id tenantId;

    public static ScopedId id(String id, String tenantId) {
        return id(id, Id.id(tenantId));
    }

    public static ScopedId id(String id, Id tenantId) {
        return id(Id.id(id), tenantId);
    }

    public static ScopedId id(Id id, Id tenantId) {
        return new ScopedId(id, tenantId);
    }

    public Id id() {
        return id;
    }

    public Id tenantId() {
        return tenantId;
    }

    public boolean isEmpty() {
        return id.isEmpty();
    }

    private ScopedId(Id id, Id tenantId) {
        this.id = id;
        this.tenantId = tenantId;
    }

}
