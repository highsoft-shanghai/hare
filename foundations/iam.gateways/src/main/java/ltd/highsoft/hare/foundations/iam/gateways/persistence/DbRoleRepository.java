package ltd.highsoft.hare.foundations.iam.gateways.persistence;

import jakarta.annotation.Resource;
import ltd.highsoft.hare.foundations.iam.domain.Role;
import ltd.highsoft.hare.foundations.iam.domain.Roles;
import ltd.highsoft.hare.frameworks.domain.core.Id;
import ltd.highsoft.hare.frameworks.domain.core.Name;
import ltd.highsoft.hare.frameworks.domain.core.ScopedId;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class DbRoleRepository implements Roles.RoleRepository {

    private @Resource RoleMapper mapper;

    @Override
    @Transactional(readOnly = true)
    public Role get(Id id) {
        return mapper.get(id);
    }

    @Override
    @Transactional(readOnly = true)
    public Role get(ScopedId id) {
        return mapper.get(id);
    }

    @Override
    @Transactional
    public void save(Role role) {
        mapper.save(role);
    }

    @Override
    public boolean exists(Name name, ScopedId id) {
        return mapper.exists(name, id);
    }

    @Override
    @Transactional
    public void remove(ScopedId id) {
        mapper.remove(id);
    }

}
