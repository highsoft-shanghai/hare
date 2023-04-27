package ltd.highsoft.hare.foundations.iam.domain;

import java.util.*;

public interface Authorities {

    void get(Authority feature);

    List<Authority> all();

    void clear();

}
