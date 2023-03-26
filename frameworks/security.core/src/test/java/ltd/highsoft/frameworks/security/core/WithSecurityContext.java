package ltd.highsoft.frameworks.security.core;

import org.junit.jupiter.api.extension.ExtendWith;

import java.lang.annotation.*;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Inherited
@ExtendWith(SecurityContextExtension.class)
public @interface WithSecurityContext {

    String[] grantedAuthorities();

}
