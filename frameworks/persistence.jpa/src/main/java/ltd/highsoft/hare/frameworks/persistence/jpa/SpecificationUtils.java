package ltd.highsoft.hare.frameworks.persistence.jpa;

import lombok.*;

import static org.apache.commons.lang3.StringUtils.defaultString;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class SpecificationUtils {

    public static String fullLike(String keyword) {
        return "%" + defaultString(keyword) + "%";
    }

}
