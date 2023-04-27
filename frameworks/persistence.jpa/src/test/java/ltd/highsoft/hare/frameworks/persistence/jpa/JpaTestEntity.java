package ltd.highsoft.hare.frameworks.persistence.jpa;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.*;

import static ltd.highsoft.hare.frameworks.domain.core.Id.id;

@Setter
@Getter
@Entity(name = "test_entities")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class JpaTestEntity {

    private @Id String id;
    private @Column(name = "name") String name;
    private @Column(name = "quantity") Integer quantity;

    public JpaTestEntity(TestEntity entity) {
        this.id = entity.id().asString();
        this.name = entity.name();
        this.quantity = entity.quantity();
    }

    public TestEntity asDomain() {
        return new TestEntity(id(id), name, quantity);
    }

    public String name() {
        return name;
    }

}
