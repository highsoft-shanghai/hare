package ltd.highsoft.hare.frameworks.domain.core;

import lombok.*;
import org.apache.commons.lang3.StringUtils;

@ToString
@EqualsAndHashCode
public class Code {

    public static final int MAX_LENGTH = 100;
    public static final int MIN_LENGTH = 1;

    private final String value;

    public static Code code(String value) {
        return new Code(value);
    }

    public Code(String value) {
        this.value = value;
        verify();
    }

    public String asString() {
        return this.value;
    }

    private void verify() {
        if (StringUtils.length(value) < MIN_LENGTH || StringUtils.length(value) > MAX_LENGTH) {
            throw new BadInputException(I18nMessage.message("error.code-length-error", 1, MAX_LENGTH));
        }
    }

}
