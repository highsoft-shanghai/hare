package ltd.highsoft.hare.frameworks.test.context;

import ltd.highsoft.hare.frameworks.domain.core.FixedIdGenerator;
import ltd.highsoft.hare.frameworks.domain.core.GlobalIdGeneratorResetter;
import ltd.highsoft.hare.frameworks.domain.core.UuidBasedIdGenerator;
import org.junit.jupiter.api.extension.AfterEachCallback;
import org.junit.jupiter.api.extension.BeforeEachCallback;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.platform.commons.util.AnnotationUtils;

import static ltd.highsoft.hare.frameworks.domain.core.Id.id;

public class GlobalIdGeneratorExtension implements BeforeEachCallback, AfterEachCallback {

    @Override
    public void beforeEach(ExtensionContext context) {
        var annotation = AnnotationUtils.findAnnotation(context.getRequiredTestClass(), WithFixedGlobalIdGenerator.class);
        annotation.ifPresent(x -> GlobalIdGeneratorResetter.reset(new FixedIdGenerator(id(x.value()))));
    }

    @Override
    public void afterEach(ExtensionContext context) {
        GlobalIdGeneratorResetter.reset(new UuidBasedIdGenerator());
    }

}
