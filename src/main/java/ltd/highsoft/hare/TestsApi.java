package ltd.highsoft.hare;

import jakarta.annotation.Resource;
import ltd.highsoft.hare.domain.Persons;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Transactional
public class TestsApi {

    private @Resource Persons persons;

    @GetMapping("/test")
    public Object test() {
        return persons.get("f354b3d03119475899de312f80c4c99e");
    }

}
