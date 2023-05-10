package ltd.highsoft.hare.foundations.iam.domain;

import lombok.AllArgsConstructor;
import ltd.highsoft.hare.frameworks.domain.core.BadInputException;
import ltd.highsoft.hare.frameworks.domain.core.Id;
import ltd.highsoft.hare.frameworks.domain.core.Name;
import ltd.highsoft.hare.frameworks.domain.core.ScopedId;

import static ltd.highsoft.hare.frameworks.domain.core.I18nMessage.message;

@AllArgsConstructor
public class Roles {

    private final RoleRepository roleRepository;
    private final UserAccounts userAccounts;

    public void add(Role role) {
        if (roleRepository.exists(role.name(), role.id())) throw new BadInputException(message("error.duplicate-role"));
        roleRepository.save(role);
    }

    public Role get(Id id) {
        return roleRepository.get(id);
    }

    public Role get(ScopedId id) {
        return roleRepository.get(id);
    }

    public void remove(ScopedId id) {
        if (userAccounts.existsByRoleId(id.id().asString())) throw new BadInputException(message("error.role-is-used"));
        roleRepository.remove(id);
    }

    public interface RoleRepository {
        void save(Role role);
        boolean exists(Name name, ScopedId id);
        Role get(Id id);
        Role get(ScopedId id);
        void remove(ScopedId id);
    }

}
