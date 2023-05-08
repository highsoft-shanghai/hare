package ltd.highsoft.hare.foundations.iam.application;

import ltd.highsoft.hare.foundations.iam.domain.Role;
import ltd.highsoft.hare.frameworks.domain.core.Code;
import ltd.highsoft.hare.frameworks.domain.core.Id;
import ltd.highsoft.hare.frameworks.domain.core.Name;
import ltd.highsoft.hare.frameworks.domain.core.Remarks;

import java.util.Set;

public record RoleContent(Id id, Code code, Name name, Set<String> authorities, Remarks remarks) {

    public static RoleContent build(Role role) {
        return new RoleContent(role.id().id(), role.code(), role.name(), role.grantedAuthorities().asSet(), role.remarks());
    }

}
