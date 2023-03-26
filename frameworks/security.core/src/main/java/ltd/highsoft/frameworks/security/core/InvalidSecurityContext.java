package ltd.highsoft.frameworks.security.core;

import ltd.highsoft.frameworks.domain.core.*;

public class InvalidSecurityContext implements SecurityContext {

    @Override
    public Id token() {
        throw new BadAccessTokenException();
    }

    @Override
    public GrantedAuthorities grantedAuthorities() {
        throw new BadAccessTokenException();
    }

    @Override
    public boolean valid() {
        return false;
    }

}
