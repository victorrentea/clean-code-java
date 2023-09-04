package victor.training.cleancode;

import org.jooq.lambda.tuple.Tuple;
import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.Map;

import static java.util.stream.Collectors.joining;

public class MicroTypesTuples {

    //<editor-fold desc="Unknown source of data">
    public Map<CustomerId, List<ProductCount>> extremeFP() {
        Long customerId = 1L;
        Integer product1Count = 2;
        Integer product2Count = 4;
        return Map.of(new CustomerId(customerId), List.of(
                new ProductCount("Table", product1Count),
                new ProductCount("Chair", product2Count)
        ));
    }
    //</editor-fold>

    record ProductCount(String productName, int count){
    }
    record CustomerId(long id) {}
    @Test
    void lackOfAbstractions() {
//        if (true) throw new FileNotFoundException();
        Map<CustomerId, List<ProductCount>> map = extremeFP();
        // Joke: try "var" above :)

        for (CustomerId cid : map.keySet()) {
            String pl = map.get(cid).stream()
                    .map(t -> t.count + " pcs. of " + t.productName)
                    .collect(joining(", "));
            System.out.println("cid=" + cid + " got " + pl);
        }
    }
}