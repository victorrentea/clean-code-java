package victor.training.cleancode;

import lombok.Value;
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

    @Value
    static class CustomerId {
        long id;
    }

    @Value
    static class ProductCount {
        String productName;
        int count;
    }


    @Test
    void lackOfAbstractions() {
        Map<CustomerId, List<ProductCount>> map = extremeFP();
        // Joke: try "var" above :)

        for (CustomerId cid : map.keySet()) {
            String pl = map.get(cid).stream()
                    .map(productCount -> productCount.getProductName() + " of " + productCount.count)
                    .collect(joining(", "));
            System.out.println("cid=" + cid + " got " + pl);
        }
    }
}