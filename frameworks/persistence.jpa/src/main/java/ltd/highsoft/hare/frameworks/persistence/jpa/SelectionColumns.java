package ltd.highsoft.hare.frameworks.persistence.jpa;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import jakarta.persistence.criteria.Selection;

public interface SelectionColumns<T, X> {

    Selection<X> toSelection(Root<T> root, CriteriaQuery<X> query, CriteriaBuilder criteriaBuilder);

}
