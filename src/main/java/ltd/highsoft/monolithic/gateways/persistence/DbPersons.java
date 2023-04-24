package ltd.highsoft.monolithic.gateways.persistence;

import ltd.highsoft.monolithic.domain.*;
import org.springframework.stereotype.Component;

@Component
public class DbPersons implements Persons {

    private final PersonMapper mapper;

    public DbPersons(PersonMapper mapper) {
        this.mapper = mapper;
    }

    @Override
    public Person get(String id) {
        return mapper.get(id);
    }

    @Override
    public void add(Person person) {
        this.mapper.add(person);
    }

}
