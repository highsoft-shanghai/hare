package ltd.highsoft.monolithic.gateways.persistence;

import ltd.highsoft.monolithic.domain.Person;
import ltd.highsoft.monolithic.domain.Persons;
import org.springframework.stereotype.Component;

@Component
public class DbPersons implements Persons {

    private final PersonMapper mapper;

    public DbPersons(PersonMapper mapper) {
        this.mapper = mapper;
    }

    @Override
    public Person forId(String id) {
        return mapper.get(id);
    }

}
