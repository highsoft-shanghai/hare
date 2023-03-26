package ltd.highsoft.frameworks.test.web;

import lombok.*;
import org.springframework.restdocs.payload.*;
import org.springframework.restdocs.snippet.Attributes;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class ConstrainedFields {

    public static FieldDescriptor constrainedFieldWithPath(String path, String constraints) {
        return PayloadDocumentation.fieldWithPath(path).attributes(
            Attributes.key("constraints").value(constraints)
        );
    }

}
