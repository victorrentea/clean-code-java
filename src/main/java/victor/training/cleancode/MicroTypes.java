package victor.training.cleancode;

import lombok.Value;
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
//              productCountOf("Table", product1Count),
//              productCountOf("Chair", product2Count)
        ));
    }
    //</editor-fold>

    @Value
    static class CustomerId {
        long id;
    }
    @Value
    static class ProductCount{
        String productName;
        int itemCount;

        public ProductCount(String productName, int itemCount) {
            this.productName = Objects.requireNonNull(productName);
            this.itemCount = itemCount;
        }

//        public static ProductCount oneItem(String productName) {
//            return new ProductCount(productName, 1);
//        }
//        public static ProductCount productCountOf(String productName, int itemCount) {
//            return new ProductCount(productName, itemCount);
//        }
    }

    @Test
    void lackOfAbstractions() {
        Map<CustomerId, List<ProductCount>> map = extremeFP();
        // Joke: try "var" above :) : only use var in tests.

        for (CustomerId cid : map.keySet()) {
            String pl = map.get(cid).stream()
                    .map(t -> t.getItemCount() + " of " + t.getProductName())
                    .collect(joining(", "));
            System.out.println("cid=" + cid.getId() + " got " + pl);
        }
    }
}