package ltd.highsoft.frameworks.context.core;

import ltd.highsoft.frameworks.domain.core.*;

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
