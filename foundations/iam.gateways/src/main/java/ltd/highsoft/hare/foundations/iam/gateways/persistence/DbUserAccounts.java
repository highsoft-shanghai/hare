package ltd.highsoft.hare.foundations.iam.gateways.persistence;

import jakarta.annotation.Resource;
import ltd.highsoft.hare.foundations.iam.domain.UserAccount;
import ltd.highsoft.hare.foundations.iam.domain.UserAccounts;
import ltd.highsoft.hare.frameworks.domain.core.Id;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Component
public class DbUserAccounts implements UserAccounts {

    private @Resource UserAccountMapper mapper;

    @Override
    @Transactional
    public void add(UserAccount userAccount) {
        mapper.add(userAccount);
    }

    @Override
    @Transactional
    public void remove(Id id) {
        mapper.remove(id);
    }

    @Override
    @Transactional(readOnly = true)
    public UserAccount get(Id id) {
        return mapper.get(id);
    }

    @Override
    public List<UserAccount> searchByRole(String roleId) {
        return mapper.list(roleId);
    }

}
