package ltd.highsoft.frameworks.domain.core;

import org.apache.commons.lang3.StringUtils;

public class SimplePasswordEncoder implements PasswordEncoder {

    @Override
    public boolean matches(String plain, String encrypted) {
        return StringUtils.equals(plain, encrypted);
    }

    @Override
    public String encode(String plain) {
        return plain;
    }

}
