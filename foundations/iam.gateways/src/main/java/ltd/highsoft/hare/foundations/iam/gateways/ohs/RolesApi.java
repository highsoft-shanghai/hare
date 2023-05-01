package ltd.highsoft.hare.foundations.iam.gateways.ohs;

import jakarta.annotation.Resource;
import ltd.highsoft.hare.foundations.iam.application.*;
import ltd.highsoft.hare.frameworks.domain.core.GeneralPagination;
import ltd.highsoft.hare.frameworks.domain.core.ObjectSink;
import ltd.highsoft.hare.frameworks.domain.core.Payload;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

import static ltd.highsoft.hare.frameworks.domain.core.Id.id;

@RestController
@RequestMapping("/roles")
public class RolesApi {

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
    @Deprecated
    public Object search(@RequestParam(required = false) String q, Pageable pageable) {
        PageRequest of = PageRequest.of(pageable.getPageNumber(), pageable.getPageSize(), pageable.getSort());
        return searchRolesUseCase.execute(q, GeneralPagination.of(of.getPageNumber(), of.getPageSize()));
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
