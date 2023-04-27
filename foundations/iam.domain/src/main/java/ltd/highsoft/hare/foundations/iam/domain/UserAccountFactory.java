package ltd.highsoft.hare.foundations.iam.domain;

import ltd.highsoft.hare.frameworks.domain.core.Id;
import ltd.highsoft.hare.frameworks.domain.core.IdGenerator;

import java.util.Set;

public class UserAccountFactory {

    private final IdGenerator idGenerator;
    private final Roles roles;

    public UserAccountFactory(IdGenerator idGenerator, Roles roles) {
        this.idGenerator = idGenerator;
        this.roles = roles;
    }

    public UserAccount newUserAccount(String name, UserAccountOwner owner, Set<String> roleIds) {
        return newUserAccountWithId(idGenerator.nextId(), name, owner, roleIds);
    }

    public UserAccount newUserAccountWithId(Id id, String name, UserAccountOwner owner, Set<String> roleIds) {
        return new UserAccount(id, name, owner, new UserAccountRoles(roleIds, roles), false);
    }

}
