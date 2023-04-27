package ltd.highsoft.hare.frameworks.test.web;

import ltd.highsoft.hare.frameworks.domain.core.Id;
import ltd.highsoft.hare.frameworks.security.core.Context;
import ltd.highsoft.hare.frameworks.security.core.ContextProvider;

import java.util.Optional;

public class MockContextProvider implements ContextProvider {

    @Override
    public Optional<Context> get(Id id) {
        return GlobalTestContext.token().orElse(Id.EMPTY).equals(id) ? GlobalTestContext.context() : Optional.empty();
    }

}
