package ltd.highsoft.hare.frameworks.domain.core;

public interface PasswordEncoder {

    boolean matches(String plain, String encrypted);

}
