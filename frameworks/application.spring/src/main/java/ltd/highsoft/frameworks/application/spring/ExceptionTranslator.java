package ltd.highsoft.frameworks.application.spring;

import ltd.highsoft.frameworks.application.core.ApplicationException;

import java.util.*;
import java.util.function.Function;

public class ExceptionTranslator {

    private final Map<Class<?>, Function<Throwable, ApplicationException>> mappers = new HashMap<>();

    public <T extends Throwable> void map(Class<T> exceptionClass, Function<Throwable, ApplicationException> mapper) {
        mappers.put(exceptionClass, mapper);
    }

    public ApplicationException translate(Throwable throwable) {
        if (throwable instanceof ApplicationException) return (ApplicationException) throwable;
        return mappers.getOrDefault(throwable.getClass(), ApplicationException::new).apply(throwable);
    }

}
