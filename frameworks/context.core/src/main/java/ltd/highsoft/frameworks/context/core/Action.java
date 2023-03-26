package ltd.highsoft.frameworks.context.core;

import java.time.Instant;

public class Action {

    private final AssociatedActor actor;
    private final Instant at;

    public Action(AssociatedActor actor, Instant at) {
        this.actor = actor;
        this.at = at;
    }

    public AssociatedActor actor() {
        return actor;
    }

    public Instant at() {
        return at;
    }

}
