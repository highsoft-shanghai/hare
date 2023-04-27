package ltd.highsoft.hare.frameworks.test.container;

import org.junit.jupiter.api.extension.*;

public class TestContainersInitializerExtension implements BeforeAllCallback {

    @Override
    public void beforeAll(ExtensionContext context) {
        new Containers<>(context, WithTestContainers.class, WithTestContainers::containers).startContainer();
    }

}
