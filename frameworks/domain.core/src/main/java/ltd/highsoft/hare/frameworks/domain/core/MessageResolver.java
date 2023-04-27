package ltd.highsoft.hare.frameworks.domain.core;

public interface MessageResolver {

    String resolve(String code, Object... args);

}
