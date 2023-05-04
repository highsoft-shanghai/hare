package ltd.highsoft.hare.foundations.iam.domain;

import ltd.highsoft.hare.frameworks.domain.core.Id;

public interface Tenants {

    void add(Tenant tenant);

    Tenant get(Id id);

    void remove(Id id);

}

