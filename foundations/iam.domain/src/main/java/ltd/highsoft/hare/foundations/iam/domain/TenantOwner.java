package ltd.highsoft.hare.foundations.iam.domain;

import ltd.highsoft.hare.frameworks.domain.core.Id;

public record TenantOwner(Id id, Tenants tenants) {

    public Tenant get() {
        return tenants().get(id);
    }

}
