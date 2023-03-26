package ltd.highsoft.frameworks.test.web;

import ltd.highsoft.frameworks.security.core.GrantedAuthorities;
import org.junit.jupiter.api.extension.*;

import java.util.Optional;

import static org.junit.platform.commons.util.AnnotationUtils.findAnnotation;

public class WithGrantedAuthoritiesExtension implements BeforeEachCallback, AfterEachCallback {

    @Override
    public void beforeEach(ExtensionContext context) {
        annotation(context).ifPresent(x -> GlobalTestContext.setup(GrantedAuthorities.of(x.value())));
    }

    @Override
    public void afterEach(ExtensionContext context) {
        GlobalTestContext.teardown();
    }

    private Optional<WithGrantedAuthorities> annotation(ExtensionContext context) {
        var result = findAnnotation(context.getRequiredTestMethod(), WithGrantedAuthorities.class);
        return result.isPresent() ? result : findAnnotation(context.getRequiredTestClass(), WithGrantedAuthorities.class);
    }

}
