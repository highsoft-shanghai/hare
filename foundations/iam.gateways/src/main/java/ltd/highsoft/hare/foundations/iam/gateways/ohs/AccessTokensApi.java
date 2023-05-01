package ltd.highsoft.hare.foundations.iam.gateways.ohs;

import jakarta.annotation.Resource;
import ltd.highsoft.hare.foundations.iam.application.GetAccessTokenContentUseCase;
import ltd.highsoft.hare.frameworks.domain.core.ObjectSink;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/access-tokens")
public class AccessTokensApi {

    private @Resource GetAccessTokenContentUseCase getAccessTokenContentUseCase;

    @GetMapping("current")
    public Optional<ObjectSink> getContent() {
        return getAccessTokenContentUseCase.execute();
    }

}
