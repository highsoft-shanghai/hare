package ltd.highsoft.hare.foundations.iam.domain;

import ltd.highsoft.hare.frameworks.domain.core.Id;

public class User {

    private final Id id;
    private final String name;
    private final boolean predefined;

    public static User restore(String userId, String userName, boolean predefined) {
        return new User(userId, userName, predefined);
    }

    public User(String id, String name, boolean predefined) {
        this.id = Id.id((id));
        this.name = name;
        this.predefined = predefined;
    }

//    public Identity asIdentity() {
//        return identity(id.asString(), name);
//    }

    public Id id() {
        return this.id;
    }

    public String name() {
        return this.name;
    }

    public boolean predefined() {
        return predefined;
    }

}
