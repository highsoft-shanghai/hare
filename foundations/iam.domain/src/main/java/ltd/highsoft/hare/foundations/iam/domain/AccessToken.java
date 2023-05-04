package ltd.highsoft.hare.foundations.iam.domain;

import ltd.highsoft.hare.frameworks.context.core.UserContext;
import ltd.highsoft.hare.frameworks.domain.core.Id;
import ltd.highsoft.hare.frameworks.security.core.Context;
import ltd.highsoft.hare.frameworks.security.core.GrantedAuthorities;
import ltd.highsoft.hare.frameworks.security.core.SecurityContext;
import ltd.highsoft.hare.frameworks.security.core.SimpleSecurityContext;

public final class AccessToken implements Context {

    private final Id id;
    private final AccessTokenOwner owner;
    private final GrantedAuthorities grantedAuthorities;
    private final String group;

    public AccessToken(Id id, AccessTokenOwner owner, GrantedAuthorities grantedAuthorities, String group) {
        this.id = id;
        this.owner = owner;
        this.grantedAuthorities = grantedAuthorities;
        this.group = group;
    }

    public AccessToken(Context context, String group) {
        this(context.securityContext().token(), AccessTokenOwner.build(context.userContext()), context.securityContext().grantedAuthorities(), group);
    }

    public String group() {
        return group;
    }

    public AccessTokenOwner owner() {
        return owner;
    }

    public Id token() {
        return id;
    }

    public GrantedAuthorities grantedAuthorities() {
        return grantedAuthorities;
    }

    @Override
    public UserContext userContext() {
        return owner().asUserContext();
    }

    @Override
    public SecurityContext securityContext() {
        return new SimpleSecurityContext(token(), grantedAuthorities());
    }

}
