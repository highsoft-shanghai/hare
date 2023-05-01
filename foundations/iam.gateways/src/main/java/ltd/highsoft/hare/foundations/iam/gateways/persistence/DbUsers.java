package ltd.highsoft.hare.foundations.iam.gateways.persistence;

import jakarta.annotation.Resource;
import ltd.highsoft.hare.foundations.iam.domain.User;
import ltd.highsoft.hare.foundations.iam.domain.Users;
import ltd.highsoft.hare.frameworks.domain.core.Id;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class DbUsers implements Users {

    private @Resource UserMapper mapper;

    @Override
    @Transactional
    public void add(User user) {
        mapper.add(user);
    }

    @Override
    @Transactional(readOnly = true)
    public User get(Id id) {
        return mapper.get(id);
    }

    @Override
    @Transactional
    public void remove(Id id) {
        mapper.remove(id);
    }

}

