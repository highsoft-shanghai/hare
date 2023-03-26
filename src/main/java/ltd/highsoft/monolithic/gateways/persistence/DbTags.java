package ltd.highsoft.monolithic.gateways.persistence;

import jakarta.annotation.Resource;
import ltd.highsoft.monolithic.domain.Tag;
import ltd.highsoft.monolithic.domain.Tags;
import org.springframework.stereotype.Component;

@Component
public class DbTags implements Tags {

    private @Resource TagMapper mapper;

    @Override
    public Tag forId(String id) {
        return mapper.get(id);
    }

}
