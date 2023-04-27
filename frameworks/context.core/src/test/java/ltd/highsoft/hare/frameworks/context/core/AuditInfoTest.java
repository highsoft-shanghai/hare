package ltd.highsoft.hare.frameworks.context.core;

import ltd.highsoft.hare.frameworks.domain.core.MapObjectSink;
import org.junit.jupiter.api.*;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoSettings;

import java.time.Instant;
import java.util.Map;

import static ltd.highsoft.hare.frameworks.domain.core.Id.id;
import static ltd.highsoft.hare.frameworks.domain.core.Identity.identity;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;

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
    void should_provide_detailed_content() {
        given(actors.get(id("john"))).willReturn(identity("john", "John"));
        given(actors.get(id("kite"))).willReturn(identity("kite", "Kite"));
        MapObjectSink sink = new MapObjectSink();
        info.content(sink);
        assertThat(sink.values()).isEqualTo(Map.of(
            "creatorId", "john", "creatorName", "John", "createdAt", Instant.parse("2022-08-31T11:22:33Z"),
            "lastUpdaterId", "kite", "lastUpdaterName", "Kite", "lastUpdatedAt", Instant.parse("2022-09-15T11:22:33Z")
        ));
    }

    @Test
    void should_provide_basic_audit_information() {
        assertThat(info.creation()).isEqualTo(creation);
        assertThat(info.lastUpdate()).isEqualTo(lastUpdate);
    }

}
