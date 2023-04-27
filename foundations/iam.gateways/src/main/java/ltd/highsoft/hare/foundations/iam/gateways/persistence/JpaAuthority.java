package ltd.highsoft.hare.foundations.iam.gateways.persistence;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.NoArgsConstructor;
import ltd.highsoft.hare.foundations.iam.domain.Authority;

import static ltd.highsoft.hare.frameworks.domain.core.Id.id;

@Entity(name = "iam_authorities")
@NoArgsConstructor
public class JpaAuthority {

    private @Id String id;
    private @Column(name = "name") String name;
    private @Column(name = "parent_id") String parentId;
    private @Column(name = "is_leaf") Boolean isLeaf;
    private @Column(name = "remarks") String remarks;

    public JpaAuthority(Authority authority) {
        this.id = authority.id().asString();
        this.name = authority.name();
        this.parentId = authority.parentId().asString();
        this.isLeaf = authority.isLeaf();
        this.remarks = authority.remarks();
    }

    public Authority asDomain() {
        return new Authority(id(id), name, id(parentId), isLeaf, remarks);
    }

}
