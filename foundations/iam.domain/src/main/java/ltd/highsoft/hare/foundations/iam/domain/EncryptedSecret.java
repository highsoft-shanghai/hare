package ltd.highsoft.hare.foundations.iam.domain;

import ltd.highsoft.hare.frameworks.domain.core.PasswordEncoder;

public class EncryptedSecret {

    private final String value;

    public static EncryptedSecret encryptedSecret(String value) {
        return new EncryptedSecret(value);
    }

    public String asString() {
        return this.value;
    }

    public boolean match(PlanSecret plan, PasswordEncoder passwordEncoder) {
        return passwordEncoder.matches(plan.asString(), value);
    }

    protected EncryptedSecret(String value) {
        this.value = value;
    }

}
