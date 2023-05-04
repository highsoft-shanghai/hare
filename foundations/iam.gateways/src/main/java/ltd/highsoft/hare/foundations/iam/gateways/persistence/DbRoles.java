package ltd.highsoft.hare.foundations.iam.gateways.persistence;

import jakarta.annotation.Resource;
import ltd.highsoft.hare.foundations.iam.domain.Role;
import ltd.highsoft.hare.foundations.iam.domain.Roles;
import ltd.highsoft.hare.frameworks.domain.core.*;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class DbRoles implements Roles {

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
    public void add(Role role) {
        mapper.add(role);
    }

    @Override
    @Transactional
    public void remove(ScopedId id) {
        mapper.remove(id);
    }

}
