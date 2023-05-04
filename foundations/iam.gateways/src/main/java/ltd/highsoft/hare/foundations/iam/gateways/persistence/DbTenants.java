package ltd.highsoft.hare.foundations.iam.gateways.persistence;

import jakarta.annotation.Resource;
import ltd.highsoft.hare.foundations.iam.domain.Tenant;
import ltd.highsoft.hare.foundations.iam.domain.Tenants;
import ltd.highsoft.hare.frameworks.domain.core.Id;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class DbTenants implements Tenants {

    private @Resource TenantMapper mapper;

    @Override
    @Transactional
    public void add(Tenant tenant) {
        mapper.add(tenant);
    }

    @Override
    @Transactional
    public void remove(Id id) {
        mapper.remove(id);
    }

    @Override
    @Transactional(readOnly = true)
    public Tenant get(Id id) {
        return mapper.get(id);
    }

}
