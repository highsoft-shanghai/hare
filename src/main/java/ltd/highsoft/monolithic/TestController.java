package ltd.highsoft.monolithic;

import jakarta.annotation.Resource;
import ltd.highsoft.monolithic.domain.Persons;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Transactional
public class TestController {

    private @Resource Persons persons;

    @GetMapping("/test")
    public Object test() {
        return persons.forId("f354b3d03119475899de312f80c4c99e");
    }

}
