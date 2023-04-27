package ltd.highsoft.hare.frameworks.domain.core;

@FunctionalInterface
public interface SupplierWithCheckedException<T> {
    T get() throws Exception;
}
