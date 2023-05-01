package ltd.highsoft.hare.foundations.iam.gateways.persistence;

import jakarta.annotation.Resource;
import ltd.highsoft.hare.foundations.iam.domain.Role;
import ltd.highsoft.hare.foundations.iam.domain.Roles;
import ltd.highsoft.hare.frameworks.domain.core.*;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Component
public class DbRoles implements Roles {

    private static final int LIMIT = 1000;

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

    @Override
    public Page<Role> search(Id tenantId, String q, Pagination pagination) {
        return mapper.search(tenantId, q, pagination);
    }

    @Override
    public List<Role> search(Id tenantId, String q) {
        return mapper.search(tenantId, q, GeneralPagination.of(0, LIMIT)).getContent();
    }

}
