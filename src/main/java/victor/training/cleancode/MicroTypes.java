package victor.training.cleancode;

import org.junit.jupiter.api.Test;

import java.util.Map;
import java.util.Map.Entry;
import java.util.function.BiFunction;
import java.util.function.Function;

import static java.util.stream.Collectors.joining;

public class MicroTypes {

    //<editor-fold desc="Unknown source of data">
    public Map<Long, Map<String, Integer>> extremeFP() {
        Long customerId = 1L;
        Integer product1Count = 2;
        Integer product2Count = 4;
        return Map.of(customerId, Map.of(
                "Table", product1Count,
                "Chair", product2Count
        ));
    }
    //</editor-fold>

    public static <K, V, R> Function<Entry<K, V>, R> entry(BiFunction<K, V, R> f) {
        return e -> f.apply(e.getKey(), e.getValue());
    }

    @Test
    void lackOfAbstractions() {
        Map<Long, Map<String, Integer>> map = extremeFP();
        // Joke: try "var" above :)

        for (Long cid : map.keySet()) {
            String pl = map.get(cid).entrySet().stream()
                    .map(entry -> entry.getValue() + " pcs. of " + entry.getKey())
                    //              .map(entry((k,v) -> ...))
                    .collect(joining(", "));
            System.out.println("cid=" + cid + " got " + pl);
        }
    }
}