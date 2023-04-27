package ltd.highsoft.hare.foundations.iam.gateways.ohs;

import jakarta.annotation.Resource;
import ltd.highsoft.hare.foundations.iam.application.SearchAllAuthoritiesUseCase;
import org.springframework.web.bind.annotation.*;

@RestController
public class AuthorityController {
    private @Resource SearchAllAuthoritiesUseCase searchAllAuthoritiesUseCase;

    @GetMapping("/authorities")
    public Object searchAll() {
        return searchAllAuthoritiesUseCase.execute();
    }
}
