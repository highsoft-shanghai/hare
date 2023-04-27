package ltd.highsoft.hare.foundations.iam.domain;

import ltd.highsoft.hare.frameworks.domain.core.Id;
import ltd.highsoft.hare.frameworks.domain.core.Identity;

import static ltd.highsoft.hare.frameworks.domain.core.Identity.identity;

public final class Tenant {

    private final Id id;
    private final String name;

    public static Tenant restore(String id, String name) {
        return new Tenant(id, name);
    }

    public Identity asIdentity() {
        return identity(id.asString(), name);
    }

    public Id id() {
        return this.id;
    }

    public String name() {
        return this.name;
    }

    private Tenant(String id, String name) {
        this.id = Id.id(id);
        this.name = name;
    }

}
