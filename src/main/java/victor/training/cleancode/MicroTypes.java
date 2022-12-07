package victor.training.cleancode;

import org.jooq.lambda.tuple.Tuple;
import org.jooq.lambda.tuple.Tuple2;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;

import static java.util.stream.Collectors.joining;

public class MicroTypes {

    //<editor-fold desc="Unknown source of data">
    public Map<CustomerId, List<OrderLine>> extremeFP() {
        Long customerId = 1L;
        Integer product1Count = 2;
        Integer product2Count = 4;
        return Map.of(new CustomerId(customerId), List.of(
                new OrderLine("Table", product1Count),
                new OrderLine("Chair", product2Count)
        ));
    }
    //</editor-fold>

    record CustomerId(long id){}
    record OrderLine(String productName, int count) {}

    @Test
    void lackOfAbstractions() {
        Map<CustomerId, List<OrderLine>> map = extremeFP();
        // Joke: try "var" above :)

        for (CustomerId cid : map.keySet()) {
            String pl = map.get(cid).stream()
                    .map(t -> t.count() + " of " + t.productName())
                    .collect(joining(", "));
            System.out.println("cid=" + cid.id() + " got " + pl);
        }
    }
}