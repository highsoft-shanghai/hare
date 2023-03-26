package ltd.highsoft.frameworks.security.core;

import ltd.highsoft.frameworks.domain.core.Id;

import java.util.Optional;

public interface ContextProvider {

    Optional<Context> get(Id id);

}
