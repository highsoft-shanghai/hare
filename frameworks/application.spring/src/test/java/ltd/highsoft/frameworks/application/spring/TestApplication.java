package ltd.highsoft.frameworks.application.spring;

import ltd.highsoft.frameworks.security.core.ContextProvider;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.Optional;

@SpringBootApplication
public class TestApplication {

    @Bean
    public ContextProvider contextProvider() {
        return token -> Optional.empty();
    }

}
