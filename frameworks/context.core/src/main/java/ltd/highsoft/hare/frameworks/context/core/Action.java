package ltd.highsoft.hare.frameworks.context.core;

import java.time.Instant;

public record Action(AssociatedActor actor, Instant at) {

}
