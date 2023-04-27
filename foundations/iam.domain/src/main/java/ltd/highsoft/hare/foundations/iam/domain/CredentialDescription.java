package ltd.highsoft.hare.foundations.iam.domain;

public class CredentialDescription {
    private final String account;
    private final String secret;

    public CredentialDescription(String account, String secret) {
        this.account = account;
        this.secret = secret;
    }

    public String account() {
        return account;
    }

    public String secret() {
        return secret;
    }

}
