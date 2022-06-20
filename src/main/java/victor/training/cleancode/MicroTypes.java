package victor.training.cleancode;

import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;

import static java.util.stream.Collectors.joining;

public class MicroTypes {

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

    record ProductCount(String productName, int items) {
    }
    record CustomerId(long id) {}

    @Test
    void lackOfAbstractions() {
        Map<CustomerId, List<ProductCount>> customerProducts = extremeFP();
        // Joke: try "var" above :)

        for (CustomerId customerId : customerProducts.keySet()) {
            List<ProductCount> value = customerProducts.get(customerId);
            String pl = value.stream()
                    .map(pc -> pc.items() + " of " + pc.productName())
                    .collect(joining(", "));
            System.out.println("cid=" + customerId.id + " got " + pl);
        }
    }
}