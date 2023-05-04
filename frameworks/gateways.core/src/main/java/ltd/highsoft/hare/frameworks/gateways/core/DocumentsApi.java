package ltd.highsoft.hare.frameworks.gateways.core;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/docs")
public class DocumentsApi {

    @RequestMapping
    public String docs() {
        return "apidoc.html";
    }

}
