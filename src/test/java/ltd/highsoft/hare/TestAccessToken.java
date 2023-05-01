package ltd.highsoft.hare;

import jakarta.annotation.Resource;
import ltd.highsoft.hare.foundations.iam.domain.AccessToken;
import ltd.highsoft.hare.foundations.iam.domain.AccessTokens;
import ltd.highsoft.hare.frameworks.test.web.GlobalTestContext;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@Transactional
public class TestAccessToken {

    private @Resource AccessTokens accessTokens;
    private AccessToken accessToken;

    public void setup() {
        GlobalTestContext.context().ifPresent(context -> {
            this.accessToken = new AccessToken(context, "web");
            accessTokens.add(accessToken);
        });
    }

    public void teardown() {
        if (accessToken != null) {
            accessTokens.remove(accessToken.token());
            this.accessToken = null;
        }
    }

}
