package ltd.highsoft.hare.foundations.iam.domain;

public final class LoginName {

    private final String value;

    public static LoginName loginName(String value) {
        return new LoginName(value);
    }

    public String asString() {
        return value;
    }

    private LoginName(String value) {
        this.value = value;
    }

}
