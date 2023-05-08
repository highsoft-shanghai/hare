package ltd.highsoft.hare.foundations.iam.domain;

import ltd.highsoft.hare.frameworks.domain.core.Code;
import ltd.highsoft.hare.frameworks.domain.core.Id;
import ltd.highsoft.hare.frameworks.domain.core.Identity;
import ltd.highsoft.hare.frameworks.domain.core.Name;

import static ltd.highsoft.hare.frameworks.domain.core.Identity.identity;

public final class Tenant {

    private final Id id;
    private final Code code;
    private final Name name;

    public Tenant(Id id, Code code, Name name) {
        this.id = id;
        this.code = code;
        this.name = name;
    }

    public Identity asIdentity() {
        return identity(id.asString(), name.asString());
    }

    public Id id() {
        return this.id;
    }

    public Code code() {
        return this.code;
    }

    public Name name() {
        return this.name;
    }
}
