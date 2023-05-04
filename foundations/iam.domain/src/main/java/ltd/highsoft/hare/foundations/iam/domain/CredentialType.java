package ltd.highsoft.hare.foundations.iam.domain;

import lombok.EqualsAndHashCode;
import lombok.ToString;

@ToString
@EqualsAndHashCode
public final class CredentialType {

    public static final CredentialType USERNAME_AND_PASSWORD = credentialType("username-and-password");

    private final String value;

    public static CredentialType credentialType(String value) {
        return new CredentialType(value);
    }

    public String asString() {
        return value;
    }

    private CredentialType(String value) {
        this.value = value;
    }

}
