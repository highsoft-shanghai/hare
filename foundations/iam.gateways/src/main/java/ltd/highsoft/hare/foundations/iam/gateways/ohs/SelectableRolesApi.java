package ltd.highsoft.hare.foundations.iam.gateways.ohs;

import jakarta.annotation.Resource;
import ltd.highsoft.hare.foundations.iam.application.GetSelectableRoleUseCase;
import ltd.highsoft.hare.foundations.iam.application.SearchSelectableRolesUseCase;
import ltd.highsoft.hare.frameworks.domain.core.ObjectSink;
import ltd.highsoft.hare.frameworks.domain.core.ValueSink;
import org.springframework.web.bind.annotation.*;

import static ltd.highsoft.hare.frameworks.domain.core.Id.id;

@RestController
@RequestMapping("/selectable-roles")
public class SelectableRolesApi {

    private @Resource SearchSelectableRolesUseCase searchSelectableRolesUseCase;
    private @Resource GetSelectableRoleUseCase getSelectableRoleUseCase;

    @GetMapping
    public ValueSink search(@RequestParam(required = false) String q) {
        return searchSelectableRolesUseCase.execute(q);
    }

    @GetMapping("{id}")
    public ObjectSink get(@PathVariable String id) {
        return getSelectableRoleUseCase.execute(id(id));
    }

}
