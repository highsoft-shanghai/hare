package ltd.highsoft.frameworks.domain.core;

@FunctionalInterface
public interface RunnableWithCheckedException {
    void run() throws Exception;
}
