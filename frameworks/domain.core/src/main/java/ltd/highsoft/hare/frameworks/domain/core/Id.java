package ltd.highsoft.hare.frameworks.domain.core;

import lombok.*;
import org.apache.commons.lang3.StringUtils;

@ToString
@EqualsAndHashCode
public final class Id {

    public static final Id EMPTY = id(StringUtils.EMPTY);
    private final String value;

    public static Id id(String value) {
        return new Id(value);
    }

    public String asString() {
        return value;
    }

    public boolean isEmpty() {
        return StringUtils.isEmpty(value);
    }

    private Id(String value) {
        this.value = value;
    }

}
