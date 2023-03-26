package ltd.highsoft.frameworks.domain.core;

import lombok.*;
import org.apache.commons.lang3.StringUtils;

import static ltd.highsoft.frameworks.domain.core.I18nMessage.message;
import static org.apache.commons.lang3.StringUtils.defaultString;

@ToString
@EqualsAndHashCode
public class Remarks {

    public static final int MAX_LENGTH = 2000;

    private final String value;

    public static Remarks remarks(String value) {
        return new Remarks(value);
    }

    public Remarks(String value) {
        this.value = defaultString(value);
        verify();
    }

    public String asString() {
        return this.value;
    }

    private void verify() {
        if (StringUtils.length(value) > MAX_LENGTH) {
            throw new BadInputException(message("error.remarks-length-error", MAX_LENGTH));
        }
    }

}
