package ltd.highsoft.hare.foundations.iam.domain;

import lombok.Getter;
import ltd.highsoft.hare.frameworks.domain.core.I18nMessage;

@Getter
public final class LoginResult {

    private final String id;
    private final boolean success;
    private final String accessToken;
    private final I18nMessage reason;

    public static LoginResult success(String loginId, String accessToken) {
        return new LoginResult(loginId, true, accessToken, I18nMessage.EMPTY);
    }

    public static LoginResult fail(String loginId, I18nMessage reason) {
        return new LoginResult(loginId, false, "", reason);
    }

    private LoginResult(String id, boolean success, String accessToken, I18nMessage reason) {
        this.id = id;
        this.success = success;
        this.accessToken = accessToken;
        this.reason = reason;
    }

}
