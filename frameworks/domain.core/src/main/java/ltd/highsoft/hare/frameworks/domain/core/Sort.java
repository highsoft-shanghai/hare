package ltd.highsoft.hare.frameworks.domain.core;

import java.util.List;

public interface Sort extends Iterable<SortOrder> {

    List<SortOrder> orders();

}
