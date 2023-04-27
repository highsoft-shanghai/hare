package ltd.highsoft.hare.foundations.iam.gateways.persistence;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.NoArgsConstructor;
import ltd.highsoft.hare.foundations.iam.domain.Tenant;

@Entity(name = "iam_tenants")
@NoArgsConstructor
public class JpaTenant {

    private @Id String id;
    private @Column(name = "name") String name;

    public JpaTenant(Tenant tenant) {
        this.id = tenant.id().asString();
        this.name = tenant.name();
    }

    Tenant asDomain() {
        return Tenant.restore(this.id, this.name);
    }

}
