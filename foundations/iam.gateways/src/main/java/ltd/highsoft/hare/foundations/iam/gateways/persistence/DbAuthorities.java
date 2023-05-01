package ltd.highsoft.hare.foundations.iam.gateways.persistence;

import jakarta.annotation.Resource;
import ltd.highsoft.hare.foundations.iam.domain.Authorities;
import ltd.highsoft.hare.foundations.iam.domain.Authority;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Component
public class DbAuthorities implements Authorities {

    private @Resource AuthorityMapper mapper;

    @Override
    @Transactional
    public void add(Authority authority) {
        mapper.add(authority);
    }

    @Override
    public List<Authority> all() {
        return mapper.all();
    }

    @Override
    @Transactional
    public void clear() {
        mapper.clear();
    }

}
