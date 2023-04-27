package ltd.highsoft.hare.frameworks.domain.core;

public class FixedIdGenerator implements IdGenerator {

    private final Id id;

    public FixedIdGenerator(Id id) {
        this.id = id;
    }

    @Override
    public Id nextId() {
        return nextReadableId();
    }

    @Override
    public Id nextReadableId() {
        return id;
    }

}
