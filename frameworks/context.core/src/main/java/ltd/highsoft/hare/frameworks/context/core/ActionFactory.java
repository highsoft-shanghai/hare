package ltd.highsoft.hare.frameworks.context.core;

import ltd.highsoft.hare.frameworks.domain.core.GlobalClock;

public class ActionFactory {

    private final Actors actors;

    public ActionFactory(Actors actors) {
        this.actors = actors;
    }

    public Action newAction() {
        return new Action(new AssociatedActor(GlobalUserContext.currentUserAccountId(), actors), GlobalClock.now());
    }

}
