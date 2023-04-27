package ltd.highsoft.hare.usecases.iam;

import jakarta.annotation.Resource;
import ltd.highsoft.hare.frameworks.domain.core.Id;
import ltd.highsoft.hare.frameworks.test.web.Documentation;
import ltd.highsoft.hare.ApiTest;
import ltd.highsoft.hare.foundations.iam.domain.User;
import ltd.highsoft.hare.foundations.iam.domain.Users;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;


public class GetUserTest extends ApiTest {

    private @Resource Users users;

    @Override
    protected Documentation document() {
        return null;
    }

    @BeforeEach
    void setUp() {
        users.add(new User("001", "001", false));
    }

    @Test
    void should_be_able_to_get_content_of_current_access_token() {
        User user = users.get(Id.id("001"));
        assertThat(user.name()).isEqualTo("001");
    }

    @AfterEach
    public void tearDown() {
        users.remove(Id.id("001"));
    }

}
