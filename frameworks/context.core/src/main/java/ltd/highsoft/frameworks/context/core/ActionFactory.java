package ltd.highsoft.frameworks.context.core;

import ltd.highsoft.frameworks.domain.core.GlobalClock;

import static ltd.highsoft.frameworks.context.core.GlobalUserContext.currentUserAccountId;

public class ActionFactory {

    private final Actors actors;

    public ActionFactory(Actors actors) {
        this.actors = actors;
    }

    public Action newAction() {
        return new Action(new AssociatedActor(currentUserAccountId(), actors), GlobalClock.now());
    }

}
