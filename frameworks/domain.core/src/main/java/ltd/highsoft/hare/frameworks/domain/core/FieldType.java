package ltd.highsoft.hare.frameworks.domain.core;

import javax.annotation.Nullable;
import java.util.function.*;

public abstract class FieldType<T> {

    private Function<String, T> nullHandler = this::defaultNullHandler;

    public FieldType<T> allowNull() {
        this.nullHandler = path -> null;
        return this;
    }

    public ArrayFieldType<T> array() {
        return new ArrayFieldType<>(this);
    }

    public StreamFieldType<T> stream() {
        return new StreamFieldType<>(this);
    }

    protected T from(@Nullable Object value, String path) {
        if (value == null) return nullHandler.apply(path);
        Class<?> aClass = value.getClass();
        if (!match(aClass)) throw new MalformedPayloadException(I18nMessage.message("error.request.field-type-mismatch", path, getClass().getSimpleName(), aClass.getSimpleName()));
        T result = convert(value, path);
        validate(result, path);
        return result;
    }

    protected final void setNullHandler(Function<String, T> nullHandler) {
        this.nullHandler = nullHandler;
    }

    protected T defaultNullHandler(String path) {
        throw new MalformedPayloadException(I18nMessage.message("error.request.missing-field", path, getClass().getSimpleName()));
    }

    protected abstract boolean match(Class<?> underlyingType);

    protected abstract T convert(Object value, String path);

    protected void validate(T value, String path) {
    }

}
