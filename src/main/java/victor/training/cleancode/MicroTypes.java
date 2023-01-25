package victor.training.cleancode;

import org.jooq.lambda.tuple.Tuple;
import org.jooq.lambda.tuple.Tuple2;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static java.util.stream.Collectors.joining;

public class MicroTypes {

    //<editor-fold desc="Unknown source of data">
    public Map<Long, List<Tuple2<String, Integer>>> extremeFP() {
        Long customerId = 1L;
        Integer product1Count = 2;
        Integer product2Count = 4;
        Map<Long, List<Tuple2<String, Integer>>> results = new HashMap<>();
        results.put(customerId, Arrays.asList(Tuple.tuple("Table", product1Count),
                Tuple.tuple("Chair", product2Count)));
        return results;
    }
    //</editor-fold>

    @Test
    void lackOfAbstractions() {
        Map<Long, List<Tuple2<String, Integer>>> map = extremeFP();
        // Joke: try "var" above :)

        for (Long cid : map.keySet()) {
            String pl = map.get(cid).stream()
                    .map(t -> t.v2 + " pcs. of " + t.v1)
                    .collect(joining(", "));
            System.out.println("cid=" + cid + " got " + pl);
        }
    }
}