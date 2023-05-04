package ltd.highsoft.hare.frameworks.context.core;

import ltd.highsoft.hare.frameworks.domain.core.GlobalClockResetter;
import org.junit.jupiter.api.*;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoSettings;

import java.time.Instant;

import static ltd.highsoft.hare.frameworks.context.core.GlobalUserContextTest.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;

@MockitoSettings
class ActionTest {

    private @Mock Actors actors;

    @BeforeEach
    void setUp() {
        GlobalUserContextResetter.reset(new SimpleUserContext(JOHN_AT_HIGHSOFT, JOHN, HIGHSOFT));
        GlobalClockResetter.fixAt(Instant.parse("2022-08-30T11:22:33Z"));
    }

    @Test
    void should_hold_operator_and_operation_time() {
        given(actors.get(JOHN_AT_HIGHSOFT.id())).willReturn(JOHN_AT_HIGHSOFT);
        ActionFactory factory = new ActionFactory(actors);
        Action action = factory.newAction();
        assertThat(action.actor().id()).isEqualTo(JOHN_AT_HIGHSOFT.id());
        assertThat(action.actor().value()).isEqualTo(JOHN_AT_HIGHSOFT);
        assertThat(action.at()).isEqualTo(Instant.parse("2022-08-30T11:22:33Z"));
    }

    @AfterEach
    void tearDown() {
        GlobalUserContextResetter.clear();
        GlobalClockResetter.reset();
    }

}
