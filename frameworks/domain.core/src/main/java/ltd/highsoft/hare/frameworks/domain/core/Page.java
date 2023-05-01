package ltd.highsoft.hare.frameworks.domain.core;

import java.util.List;
import java.util.function.Function;

public interface Page<T> {

    List<T> getContent();

    int getSize();

    int getNumber();

    int getNumberOfElements();

    int getNumberOfTotalPages();

    long getNumberOfTotalElements();

    boolean getFirst();

    boolean getLast();

    <U> Page<U> map(Function<? super T, ? extends U> converter);

}
