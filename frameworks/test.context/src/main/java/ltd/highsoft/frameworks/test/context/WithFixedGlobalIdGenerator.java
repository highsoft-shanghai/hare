package ltd.highsoft.frameworks.test.context;

import org.junit.jupiter.api.extension.ExtendWith;

import java.lang.annotation.*;

@Target({ElementType.TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Inherited
@ExtendWith(GlobalIdGeneratorExtension.class)
public @interface WithFixedGlobalIdGenerator {

    String value();

}
