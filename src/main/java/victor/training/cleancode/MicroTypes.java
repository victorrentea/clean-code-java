package victor.training.cleancode;

import lombok.Value;
import org.jooq.lambda.tuple.Tuple;
import org.jooq.lambda.tuple.Tuple2;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;
import java.util.Objects;

import static java.util.stream.Collectors.joining;

public class MicroTypes {

    //<editor-fold desc="Unknown source of data">
    public Map<CustomerId, List<ProductCount>> extremeFP() {
        CustomerId customerId = new CustomerId(1L);
        Integer product1Count = 2;
        Integer product2Count = 4;
        return Map.of(customerId, List.of(
                new ProductCount("Table", product1Count),
                new ProductCount("Chair", product2Count)
        ));
    }
    //</editor-fold>

    @Value
    static class CustomerId {
        long id;
    } // java 17 kung fu
    record ProductCount(String productName, int count) {}

    @Test
    void lackOfAbstractions() {
        Map<CustomerId, List<ProductCount>> customerIdToProductdWithCount = extremeFP();

        for (CustomerId cid : customerIdToProductdWithCount.keySet()) {
            String pl = customerIdToProductdWithCount.get(cid).stream()
                    .map(tuple -> tuple.count() + " of " + tuple.productName())
                    .collect(joining(", "));
            System.out.println("cid=" + cid.id + " got " + pl);
        }
    }
}