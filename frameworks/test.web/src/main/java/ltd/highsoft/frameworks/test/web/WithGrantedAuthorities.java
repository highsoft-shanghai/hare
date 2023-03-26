package ltd.highsoft.frameworks.test.web;

import org.junit.jupiter.api.extension.ExtendWith;

import java.lang.annotation.*;

@Target({ElementType.TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Inherited
@ExtendWith(WithGrantedAuthoritiesExtension.class)
public @interface WithGrantedAuthorities {

    String[] value();

}
