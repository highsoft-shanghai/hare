package ltd.highsoft.hare.frameworks.test.moco;

import org.junit.jupiter.api.extension.ExtendWith;

import java.lang.annotation.*;

@Target({ElementType.TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Inherited
@ExtendWith(ExternalServiceExtension.class)
public @interface WithExternalService {

    int port() default 12306;

}
