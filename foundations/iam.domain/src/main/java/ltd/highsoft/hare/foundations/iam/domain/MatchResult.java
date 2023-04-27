package ltd.highsoft.hare.foundations.iam.domain;

public final class MatchResult {

    private final boolean matched;
    private final Credential credential;
    private final String reason;

    public static MatchResult success(Credential credential) {
        return new MatchResult(true, credential, "");
    }

    public static MatchResult fail(String reason) {
        return new MatchResult(false, null, reason);
    }

    private MatchResult(boolean matched, Credential credential, String message) {
        this.matched = matched;
        this.credential = credential;
        this.reason = message;
    }

    public boolean matched() {
        return this.matched;
    }

    public String reason() {
        return this.reason;
    }

    public Credential credential() {
        return this.credential;
    }

}
