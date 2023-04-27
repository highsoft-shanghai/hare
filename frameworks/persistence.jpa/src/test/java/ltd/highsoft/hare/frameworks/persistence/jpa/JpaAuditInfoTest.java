package ltd.highsoft.hare.frameworks.persistence.jpa;

import ltd.highsoft.hare.frameworks.context.core.Action;
import ltd.highsoft.hare.frameworks.context.core.Actors;
import ltd.highsoft.hare.frameworks.context.core.AssociatedActor;
import ltd.highsoft.hare.frameworks.context.core.AuditInfo;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoSettings;

import java.time.Instant;

import static ltd.highsoft.hare.frameworks.domain.core.Id.id;
import static org.assertj.core.api.Assertions.assertThat;

@MockitoSettings
class JpaAuditInfoTest {

    private @Mock Actors actors;

    @Test
    void should_be_able_to_convert_between_domain_objects() {
        Action creation = new Action(new AssociatedActor(id("john"), actors), Instant.parse("2022-08-09T11:22:33Z"));
        Action lastUpdate = new Action(new AssociatedActor(id("kite"), actors), Instant.parse("2022-09-09T11:22:33Z"));
        AuditInfo domain = new AuditInfo(creation, lastUpdate);
        assertThat(new JpaAuditInfo(domain).asDomain(actors)).usingRecursiveComparison().isEqualTo(domain);
    }

}
