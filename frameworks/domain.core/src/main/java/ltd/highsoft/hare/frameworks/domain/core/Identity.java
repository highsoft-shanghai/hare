package ltd.highsoft.hare.frameworks.domain.core;

import lombok.*;

@ToString
@EqualsAndHashCode
public final class Identity {

    private final Id id;
    private final String name;

    public static Identity identity(String id, String name) {
        return identity(Id.id(id), name);
    }

    public static Identity identity(Id id, String name) {
        return new Identity(id, name);
    }

    public Id id() {
        return id;
    }

    public String name() {
        return name;
    }

    private Identity(Id id, String name) {
        this.id = id;
        this.name = name;
    }

}
