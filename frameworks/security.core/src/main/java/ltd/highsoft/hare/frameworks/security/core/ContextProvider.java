package ltd.highsoft.hare.frameworks.security.core;

import ltd.highsoft.hare.frameworks.domain.core.Id;

import java.util.Optional;

public interface ContextProvider {

    Optional<Context> get(Id id);

}
