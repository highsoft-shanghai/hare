package ltd.highsoft.hare.frameworks.security.core;

import ltd.highsoft.hare.frameworks.domain.core.BadAccessTokenException;
import ltd.highsoft.hare.frameworks.domain.core.Id;

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
