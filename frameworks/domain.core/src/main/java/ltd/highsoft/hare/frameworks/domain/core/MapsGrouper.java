package ltd.highsoft.hare.frameworks.domain.core;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

public final class MapsGrouper<K, V> {

    public static <K, V> MapsGrouper<K, V> of(List<Map<K, V>> maps) {
        return new MapsGrouper<>(maps);
    }

    private final List<Map<K, V>> maps;

    private MapsGrouper(List<Map<K, V>> maps) {
        this.maps = maps;
    }

    public <G> Map<G, List<Map<K, V>>> by(Function<Map<K, V>, G> grouper) {
        Map<G, List<Map<K, V>>> res = new HashMap<>();
        for (Map<K, V> map : maps) {
            G applied = grouper.apply(map);
            if (res.containsKey(applied)) res.get(applied).add(map);
            else {
                List<Map<K, V>> list = new ArrayList<>();
                list.add(map);
                res.put(applied, list);
            }
        }
        return res;
    }

}
