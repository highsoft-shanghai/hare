package ltd.highsoft.hare.frameworks.persistence.jpa;

public interface CommonSelectionColumns {

    static <D> SelectionColumns<D, D> root() {
        return (root, query, builder) -> root;
    }

    static <D> SelectionColumns<D, Long> countOfRoot() {
        return (root, query, builder) -> builder.count(root);
    }

    static <D, X extends Number> SelectionColumns<D, X> sumOn(String attributeName) {
        return (root, query, builder) -> builder.sum(root.get(attributeName));
    }

}
