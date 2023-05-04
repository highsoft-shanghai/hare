package ltd.highsoft.hare.foundations.iam.domain;

import ltd.highsoft.hare.frameworks.domain.core.Id;
import ltd.highsoft.hare.frameworks.domain.core.ScopedId;

public interface Roles {

    void add(Role role);

    Role get(Id id);

    Role get(ScopedId id);

    void remove(ScopedId id);

}
