package ltd.highsoft.hare.foundations.iam.gateways.persistence;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.NoArgsConstructor;
import ltd.highsoft.hare.foundations.iam.domain.User;

@Entity(name = "iam_users")
@NoArgsConstructor
public class JpaUser {

    private @Id String id;
    private @Column(name = "name") String name;
    private @Column(name = "predefined") Boolean predefined;

    public JpaUser(User user) {
        this.id = user.id().asString();
        this.name = user.name();
        this.predefined = user.predefined();
    }

    User asDomain() {
        return new User(this.id, this.name, predefined);
    }

}
