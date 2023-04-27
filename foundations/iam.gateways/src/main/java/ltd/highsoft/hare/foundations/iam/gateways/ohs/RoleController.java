package ltd.highsoft.hare.foundations.iam.gateways.ohs;

import jakarta.annotation.Resource;
import ltd.highsoft.hare.foundations.iam.application.*;
import ltd.highsoft.hare.frameworks.domain.core.ObjectSink;
import ltd.highsoft.hare.frameworks.domain.core.Payload;
import ltd.highsoft.hare.frameworks.persistence.spring.SpringPagination;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

import static ltd.highsoft.hare.frameworks.domain.core.Id.id;

@RestController
@RequestMapping("/roles")
public class RoleController {

    private @Resource CreateRoleUseCase createRoleUseCase;
    private @Resource SearchRolesUseCase searchRolesUseCase;
    private @Resource GetRoleForUpdatingUseCase getRoleUseCase;
    private @Resource UpdateRoleUseCase updateRoleUseCase;
    private @Resource DeleteRoleUseCase deleteRoleUseCase;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Object create(@RequestBody Payload payload) {
        return Map.of("id", createRoleUseCase.execute(payload));
    }

    @GetMapping
    public Object search(@RequestParam(required = false) String q, Pageable pageable) {
        return searchRolesUseCase.execute(q, SpringPagination.of(pageable));
    }

    @GetMapping("{id}")
    public ObjectSink get(@PathVariable String id) {
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
