package ltd.highsoft.hare.foundations.iam.gateways.secure;

import ltd.highsoft.hare.frameworks.domain.core.PasswordEncoder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class SpringBCryptPasswordEncoder implements PasswordEncoder {

    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    public SpringBCryptPasswordEncoder() {
        this.bCryptPasswordEncoder = new BCryptPasswordEncoder();
    }

    @Override
    public boolean matches(String plain, String encrypted) {
        return bCryptPasswordEncoder.matches(plain, encrypted);
    }

}
