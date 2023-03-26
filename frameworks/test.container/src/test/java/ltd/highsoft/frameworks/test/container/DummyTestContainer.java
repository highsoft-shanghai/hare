package ltd.highsoft.frameworks.test.container;

import org.testcontainers.lifecycle.Startable;

public class DummyTestContainer extends TestContainer<Startable> {

    static int numberOfStarts;
    static int numberOfEnvironmentSetups;

    @Override
    protected Startable createContainer() {
        return new Startable() {
            @Override
            public void start() {
                numberOfStarts += 1;
            }

            @Override
            public void stop() {
            }
        };
    }

    @Override
    protected void setupEnvironment() {
        numberOfEnvironmentSetups += 1;
    }

}
