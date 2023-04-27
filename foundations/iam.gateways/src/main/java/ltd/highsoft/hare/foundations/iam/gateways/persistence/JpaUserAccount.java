package ltd.highsoft.hare.foundations.iam.gateways.persistence;

import jakarta.persistence.*;
import lombok.NoArgsConstructor;
import ltd.highsoft.hare.foundations.iam.domain.Roles;
import ltd.highsoft.hare.foundations.iam.domain.UserAccount;
import ltd.highsoft.hare.foundations.iam.domain.UserAccountOwner;
import ltd.highsoft.hare.foundations.iam.domain.UserAccountRoles;

import java.util.Set;

import static ltd.highsoft.hare.frameworks.domain.core.Id.id;

@Entity(name = "iam_user_accounts")
@NoArgsConstructor
public class JpaUserAccount {

    private @Id String id;
    private @Column(name = "name") String name;
    private @Column(name = "user_id") String userId;
    private @Column(name = "tenant_id") String tenantId;
    private @Column(name = "predefined") Boolean predefined;

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "iam_user_account_roles", joinColumns = @JoinColumn(name = "user_account_id"))
    private @Column(name = "role_id") Set<String> roleIds;

    public JpaUserAccount(UserAccount userAccount) {
        this.id = userAccount.id().asString();
        this.name = userAccount.name();
        this.userId = userAccount.owner().userId().asString();
        this.tenantId = userAccount.owner().tenantId().asString();
        this.roleIds = userAccount.roles().asSet();
        this.predefined = userAccount.predefined();
    }

    public UserAccount asDomain(Roles roles) {
        return new UserAccount(id(id), name, new UserAccountOwner(id(userId), id(tenantId)), new UserAccountRoles(roleIds, roles), predefined);
    }

}
