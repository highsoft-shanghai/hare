package ltd.highsoft.hare.frameworks.test.moco;

import com.github.dreamhead.moco.*;
import org.junit.jupiter.api.extension.*;
import org.junit.platform.commons.util.AnnotationUtils;

import java.util.Optional;

import static com.github.dreamhead.moco.Moco.httpServer;
import static com.github.dreamhead.moco.Runner.runner;

public class ExternalServiceExtension implements BeforeAllCallback, AfterAllCallback, ParameterResolver {

    private Runner runner;
    private HttpServer server;

    @Override
    public void beforeAll(ExtensionContext context) {
        Optional<WithExternalService> annotation = AnnotationUtils.findAnnotation(context.getRequiredTestClass(), WithExternalService.class);
        if (annotation.isEmpty()) return;
        this.server = httpServer(annotation.get().port());
        this.runner = runner(this.server);
        this.runner.start();
    }

    @Override
    public void afterAll(ExtensionContext context) {
        if (this.runner == null) return;
        this.runner.stop();
    }

    @Override
    public boolean supportsParameter(ParameterContext parameterContext, ExtensionContext extensionContext) throws ParameterResolutionException {
        return parameterContext.getParameter().getType() == HttpServer.class;
    }

    @Override
    public Object resolveParameter(ParameterContext parameterContext, ExtensionContext extensionContext) throws ParameterResolutionException {
        return this.server;
    }

}
