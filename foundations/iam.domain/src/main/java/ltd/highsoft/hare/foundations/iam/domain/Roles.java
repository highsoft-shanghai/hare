package ltd.highsoft.hare.foundations.iam.domain;

import ltd.highsoft.hare.frameworks.domain.core.Id;
import ltd.highsoft.hare.frameworks.domain.core.Page;
import ltd.highsoft.hare.frameworks.domain.core.Pagination;
import ltd.highsoft.hare.frameworks.domain.core.ScopedId;

import java.util.List;

public interface Roles {

    void add(Role role);

    Role get(Id id);

    Role get(ScopedId id);

    void remove(ScopedId id);

    Page<Role> search(Id tenantId, String q, Pagination pagination);

    List<Role> search(Id tenantId, String q);

}
