package ltd.highsoft.hare.frameworks.context.core;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoSettings;

import java.time.Instant;

import static ltd.highsoft.hare.frameworks.domain.core.Id.id;
import static org.assertj.core.api.Assertions.assertThat;

@MockitoSettings
class AuditInfoTest {

    private @Mock Actors actors;
    private Action creation;
    private Action lastUpdate;
    private AuditInfo info;

    @BeforeEach
    void setUp() {
        creation = new Action(new AssociatedActor(id("john"), actors), Instant.parse("2022-08-31T11:22:33Z"));
        lastUpdate = new Action(new AssociatedActor(id("kite"), actors), Instant.parse("2022-09-15T11:22:33Z"));
        info = new AuditInfo(creation, lastUpdate);
    }

    @Test
    void should_provide_basic_audit_information() {
        assertThat(info.creation()).isEqualTo(creation);
        assertThat(info.lastUpdate()).isEqualTo(lastUpdate);
    }

}
