package ltd.highsoft.frameworks.test.web;

import ltd.highsoft.frameworks.domain.core.Id;
import ltd.highsoft.frameworks.security.core.*;

import java.util.Optional;

public class MockContextProvider implements ContextProvider {

    @Override
    public Optional<Context> get(Id id) {
        return GlobalTestContext.token().orElse(Id.EMPTY).equals(id) ? GlobalTestContext.context() : Optional.empty();
    }

}
