package ltd.highsoft.hare.foundations.iam.gateways.persistence;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.NoArgsConstructor;
import ltd.highsoft.hare.frameworks.domain.core.ScopedId;
import ltd.highsoft.hare.frameworks.security.core.GrantedAuthorities;
import ltd.highsoft.hare.foundations.iam.domain.Role;

import static ltd.highsoft.hare.frameworks.domain.core.Name.name;
import static ltd.highsoft.hare.frameworks.domain.core.Remarks.remarks;

@Entity(name = "iam_roles")
@NoArgsConstructor
public class JpaRole {

    private @Id String id;
    private @Column(name = "name") String name;
    private @Column(name = "authorities") String authorities;
    private @Column(name = "remarks") String remarks;
    private @Column(name = "tenant_id") String tenantId;
    private @Column(name = "predefined") Boolean predefined;

    public JpaRole(Role role) {
        this.id = role.id().id().asString();
        this.name = role.name().asString();
        this.authorities = role.grantedAuthorities().toCommaSeparatedString();
        this.remarks = role.remarks().asString();
        this.tenantId = role.id().tenantId().asString();
        this.predefined = role.predefined();
    }

    public Role asDomain() {
        return new Role(ScopedId.id(id, tenantId), name(name), GrantedAuthorities.fromCommaSeparatedString(authorities), remarks(remarks), predefined);
    }

}
