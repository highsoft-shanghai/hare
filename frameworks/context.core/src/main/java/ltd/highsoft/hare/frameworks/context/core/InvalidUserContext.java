package ltd.highsoft.hare.frameworks.context.core;

import ltd.highsoft.hare.frameworks.domain.core.BadAccessTokenException;
import ltd.highsoft.hare.frameworks.domain.core.Identity;

public class InvalidUserContext implements UserContext {

    @Override
    public Identity userAccount() {
        throw new BadAccessTokenException();
    }

    @Override
    public Identity user() {
        throw new BadAccessTokenException();
    }

    @Override
    public Identity tenant() {
        throw new BadAccessTokenException();
    }

    @Override
    public boolean valid() {
        return false;
    }

}
