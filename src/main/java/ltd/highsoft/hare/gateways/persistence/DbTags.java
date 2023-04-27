package ltd.highsoft.hare.gateways.persistence;

import jakarta.annotation.Resource;
import ltd.highsoft.hare.domain.Tag;
import ltd.highsoft.hare.domain.Tags;
import org.springframework.stereotype.Component;

@Component
public class DbTags implements Tags {

    private @Resource TagMapper mapper;

    @Override
    public Tag get(String id) {
        return mapper.get(id);
    }

}
