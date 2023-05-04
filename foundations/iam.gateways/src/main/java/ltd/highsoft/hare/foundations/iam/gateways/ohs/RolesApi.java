package ltd.highsoft.hare.foundations.iam.gateways.ohs;

import jakarta.annotation.Resource;
import ltd.highsoft.hare.foundations.iam.application.*;
import ltd.highsoft.hare.frameworks.domain.core.Payload;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

import static ltd.highsoft.hare.frameworks.domain.core.Id.id;

@RestController
@RequestMapping("/roles")
public class RolesApi {

    private @Resource CreateRoleUseCase createRoleUseCase;
    private @Resource GetRoleForUpdatingUseCase getRoleUseCase;
    private @Resource UpdateRoleUseCase updateRoleUseCase;
    private @Resource DeleteRoleUseCase deleteRoleUseCase;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Object create(@RequestBody Payload payload) {
        return Map.of("id", createRoleUseCase.execute(payload));
    }

    @GetMapping("{id}")
    public RoleContent get(@PathVariable String id) {
        return getRoleUseCase.execute(id(id));
    }

    @PutMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void update(@PathVariable String id, @RequestBody Payload payload) {
        updateRoleUseCase.execute(id(id), payload);
    }

    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable String id) {
        deleteRoleUseCase.execute(id);
    }

}
