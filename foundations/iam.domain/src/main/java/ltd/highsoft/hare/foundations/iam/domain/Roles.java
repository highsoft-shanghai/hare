package ltd.highsoft.hare.foundations.iam.domain;

import lombok.AllArgsConstructor;
import ltd.highsoft.hare.frameworks.domain.core.*;

import static ltd.highsoft.hare.frameworks.domain.core.I18nMessage.message;

@AllArgsConstructor
public class Roles {

    private final RoleRepository roleRepository;
    private final UserAccounts userAccounts;

    public void add(Role role) {
        if (roleRepository.nameDuplication(role.name(), role.id()) || roleRepository.codeDuplication(role.code(), role.id()))
            throw new BadInputException(message("error.duplicate-role"));
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
        boolean nameDuplication(Name name, ScopedId id);
        boolean codeDuplication(Code code, ScopedId id);
        Role get(Id id);
        Role get(ScopedId id);
        void remove(ScopedId id);
    }

}
