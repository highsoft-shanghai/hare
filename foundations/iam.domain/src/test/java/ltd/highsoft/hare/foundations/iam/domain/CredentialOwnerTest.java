package ltd.highsoft.hare.foundations.iam.domain;

import org.junit.jupiter.api.Test;

import static ltd.highsoft.hare.frameworks.domain.core.Id.id;
import static org.assertj.core.api.Assertions.assertThat;

class CredentialOwnerTest {

    @Test
    void should_be_able_to_compare_to_others() {
        CredentialOwner ownerA = new CredentialOwner(id("john@highsoft.ltd"), id("john"), id("highsoft"));
        CredentialOwner ownerB = new CredentialOwner(id("john@highsoft.ltd"), id("john"), id("highsoft"));
        CredentialOwner ownerC = new CredentialOwner(id("kite@highsoft.ltd"), id("kite"), id("highsoft"));
        assertThat(ownerA).isEqualTo(ownerB);
        assertThat(ownerA).isNotEqualTo(ownerC);
    }

}
