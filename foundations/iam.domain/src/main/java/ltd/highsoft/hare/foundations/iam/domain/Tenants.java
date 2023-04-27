package ltd.highsoft.hare.foundations.iam.domain;

import ltd.highsoft.hare.frameworks.domain.core.Id;

import java.util.List;

public interface Tenants {

    void add(Tenant tenant);

    Tenant get(Id id);

    void remove(Id id);

    List<Tenant> get();

}

