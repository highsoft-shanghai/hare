package ltd.highsoft.hare.foundations.iam.domain;

public final class PlanSecret {

    private final String value;

    public static PlanSecret from(String value) {
        return new PlanSecret(value);
    }

    public String asString() {
        return this.value;
    }

    private PlanSecret(String value) {
        this.value = value;
    }

}
