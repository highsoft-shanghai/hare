package ltd.highsoft.hare.frameworks.domain.core;

import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.apache.commons.lang3.StringUtils;

@ToString
@EqualsAndHashCode
public class Name {

    public static final int MAX_LENGTH = 200;
    public static final int MIN_LENGTH = 1;

    private final String value;

    public static Name name(String value) {
        return new Name(value);
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
            throw new BadInputException(I18nMessage.message("error.name-length-error", MIN_LENGTH, MAX_LENGTH));
        }
    }

}
