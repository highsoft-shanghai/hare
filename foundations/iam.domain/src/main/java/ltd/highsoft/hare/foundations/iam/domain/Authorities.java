package ltd.highsoft.hare.foundations.iam.domain;

import java.util.*;

public interface Authorities {

    void add(Authority feature);

    List<Authority> all();

    void clear();

}
