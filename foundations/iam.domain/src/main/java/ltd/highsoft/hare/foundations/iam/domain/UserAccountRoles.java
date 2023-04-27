package ltd.highsoft.hare.foundations.iam.domain;

import ltd.highsoft.hare.frameworks.security.core.GrantedAuthorities;

import java.util.Set;
import java.util.stream.Stream;

import static ltd.highsoft.hare.frameworks.domain.core.Id.id;

public class UserAccountRoles {

    private final Set<String> roleIds;
    private final Roles roles;

    public UserAccountRoles(Set<String> roleIds, Roles roles) {
        this.roleIds = roleIds;
        this.roles = roles;
    }

    public GrantedAuthorities grantedAuthorities() {
        Stream<GrantedAuthorities> authorities = roleIds.stream().map(roleId -> roles.get(id(roleId)).grantedAuthorities());
        return GrantedAuthorities.of(Stream.concat(authorities, Stream.of(GrantedAuthorities.of(roleIds))));
    }

    public Set<String> asSet() {
        return roleIds;
    }

}
