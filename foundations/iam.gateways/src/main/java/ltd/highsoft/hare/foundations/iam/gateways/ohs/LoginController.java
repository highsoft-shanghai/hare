package ltd.highsoft.hare.foundations.iam.gateways.ohs;

import jakarta.annotation.Resource;
import ltd.highsoft.hare.frameworks.domain.core.Payload;
import ltd.highsoft.hare.foundations.iam.application.LoginUseCase;
import ltd.highsoft.hare.foundations.iam.domain.LoginResult;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
public class LoginController {

    private @Resource LoginUseCase loginUseCase;

    @PostMapping("/logins")
    @ResponseStatus(HttpStatus.CREATED)
    LoginResult login(@RequestBody Payload payload) {
        return loginUseCase.execute(payload);
    }

}
