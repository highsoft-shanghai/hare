package ltd.highsoft.frameworks.domain.core;

import lombok.*;
import org.apache.commons.lang3.StringUtils;

import static ltd.highsoft.frameworks.domain.core.I18nMessage.message;

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
            throw new BadInputException(message("error.code-length-error", 1, MAX_LENGTH));
        }
    }

}
