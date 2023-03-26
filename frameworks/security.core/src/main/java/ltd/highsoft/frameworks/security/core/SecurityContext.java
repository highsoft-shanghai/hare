package ltd.highsoft.frameworks.security.core;

import ltd.highsoft.frameworks.domain.core.Id;

import static ltd.highsoft.frameworks.domain.core.Id.id;

public interface SecurityContext {

    SecurityContext ANONYMOUS = new SimpleSecurityContext(id("anonymous"), GrantedAuthorities.ANONYMOUS);
    SecurityContext SYSTEM = new SimpleSecurityContext(id("system"), GrantedAuthorities.SYSTEM);
    SecurityContext INVALID = new InvalidSecurityContext();

    default void authorize(RequiredAuthorities requiredAuthorities) {
        grantedAuthorities().authorize(requiredAuthorities);
    }

    Id token();

    GrantedAuthorities grantedAuthorities();

    boolean valid();

}
