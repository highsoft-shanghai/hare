package ltd.highsoft.hare.frameworks.persistence.jpa;

import lombok.*;
import ltd.highsoft.hare.frameworks.domain.core.Id;

@ToString
@EqualsAndHashCode
public class TestEntity {

    private final Id id;
    private final String name;
    private final int quantity;

    public TestEntity(Id id, String name) {
        this.id = id;
        this.name = name;
        this.quantity = 1;
    }

    public TestEntity(Id id, String name, int quantity) {
        this.id = id;
        this.name = name;
        this.quantity = quantity;
    }

    public Id id() {
        return id;
    }

    public String name() {
        return name;
    }

    public int quantity() {
        return quantity;
    }

}
