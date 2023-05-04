package ltd.highsoft.hare.foundations.iam.domain;

import ltd.highsoft.hare.frameworks.domain.core.Id;
import ltd.highsoft.hare.frameworks.domain.core.Identity;
import ltd.highsoft.hare.frameworks.security.core.GrantedAuthorities;

import static ltd.highsoft.hare.frameworks.domain.core.Identity.identity;

public record UserAccount(Id id, String name, UserAccountOwner owner, UserAccountRoles roles, boolean predefined) {

    public Identity asIdentity() {
        return identity(id.asString(), name);
    }

    public GrantedAuthorities grantedAuthorities() {
        return roles.grantedAuthorities();
    }

}
