package ltd.highsoft.frameworks.domain.core;

public interface MessageResolver {

    String resolve(String code, Object... args);

}
