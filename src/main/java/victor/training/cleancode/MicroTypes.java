package victor.training.cleancode;

import lombok.Value;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;

import static java.util.stream.Collectors.joining;

//@Data
@Value
class ProductCount {
    String productName;
    int count;
}
// java 17 ❤️ un vis frumos
//record ProductCount2( String productName,  int count) P{}

public class MicroTypes {


    //<editor-fold desc="Unknown source of data">
    public Map<Long, List<ProductCount>> extremeFP() {
        Long customerId = 1L;
        Integer product1Count = 2;
        Integer product2Count = 4;
        return Map.of(customerId, List.of(
                new ProductCount("Table", product1Count),
                new ProductCount("Chair", product2Count)
        ));
    }
    //</editor-fold>

    @Test
    void lackOfAbstractions() {
        Map<Long, List<ProductCount>> map = extremeFP();
        // Joke: try "var" above :)

        for (Long customerId : map.keySet()) {
            String pl = map.get(customerId).stream()
                    .map(productCount -> productCount.getCount() + " of " + productCount.getProductName())
                    .collect(joining(", "));
            System.out.println("cid=" + customerId + " got " + pl);
        }
    }
}