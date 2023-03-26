package ltd.highsoft.frameworks.domain.core;

import lombok.*;
import org.apache.commons.lang3.StringUtils;

import java.util.List;

@ToString
@EqualsAndHashCode
public final class I18nMessage {

    public static final I18nMessage EMPTY = new I18nMessage("", new Object[]{});
    private final String code;
    private final Object[] data;

    public static I18nMessage message(String code, Object... data) {
        return new I18nMessage(code, data);
    }

    public String format(MessageResolver messageResolver) {
        if (StringUtils.isBlank(code)) return StringUtils.EMPTY;
        return messageResolver.resolve(code, data);
    }

    public String code() {
        return code;
    }

    public List<Object> data() {
        return List.of(data);
    }

    private I18nMessage(String code, Object[] data) {
        this.code = code;
        this.data = data;
    }

}
