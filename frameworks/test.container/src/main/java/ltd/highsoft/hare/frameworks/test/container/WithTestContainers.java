package ltd.highsoft.hare.frameworks.test.container;

import org.junit.jupiter.api.extension.ExtendWith;

import java.lang.annotation.*;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Inherited
@ExtendWith(TestContainersInitializerExtension.class)
public @interface WithTestContainers {

    Class<?>[] containers() default {};

}
