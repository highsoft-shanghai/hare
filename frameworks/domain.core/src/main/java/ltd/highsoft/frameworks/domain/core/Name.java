package ltd.highsoft.frameworks.domain.core;

import lombok.*;
import org.apache.commons.lang3.StringUtils;

import static ltd.highsoft.frameworks.domain.core.I18nMessage.message;

@ToString
@EqualsAndHashCode
public class Name {

    public static final Name EMPTY_NAME = emptyName();
    public static final int MAX_LENGTH = 200;
    public static final int MIN_LENGTH = 1;

    private final String value;

    public static Name name(String value) {
        return new Name(value);
    }

    private static Name emptyName() {
        return new Name();
    }

    private Name() {
        this.value = "";
    }

    public Name(String value) {
        this.value = value;
        verify();
    }

    public String asString() {
        return this.value;
    }

    private void verify() {
        if (StringUtils.length(value.trim()) < MIN_LENGTH || StringUtils.length(value.trim()) > MAX_LENGTH) {
            throw new BadInputException(message("error.name-length-error", MIN_LENGTH, MAX_LENGTH));
        }
    }

}
