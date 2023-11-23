package victor.training.cleancode;

import org.jooq.lambda.tuple.Tuple;
import org.jooq.lambda.tuple.Tuple2;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;

import static java.util.stream.Collectors.joining;

public class MicroTypesTuples {

    //<editor-fold desc="Unknown source of data">
    public Map<Long, List<Tuple2<String, Integer>>> extremeFP() {
        Long customerId = 1L;
        Integer product1Count = 2;
        Integer product2Count = 4;
        return Map.of(customerId, List.of(
                Tuple.tuple("Table", product1Count),
                Tuple.tuple("Chair", product2Count)
        ));
    }
    //</editor-fold>
//  private  record CustomerOrders(Long id, List<String>a ) {}
    @Test
    void lackOfAbstractions() {
        Map<Long, List<Tuple2<String, Integer>>> customerIdToOrderProductCounts = extremeFP();
        // Joke: try "var" above :)

        for (Long cid : customerIdToOrderProductCounts.keySet()) {
            String pl = customerIdToOrderProductCounts.get(cid).stream()
                    .map(t -> t.v2 + " pcs. of " + t.v1)
                    .collect(joining(", "));
            System.out.println("cid=" + cid + " got " + pl);
        }
    }
}