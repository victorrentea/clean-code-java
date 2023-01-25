package victor.training.cleancode;

import lombok.Value;
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
    public Map<CustomerId, List<ProductCount>> extremeFP() {
        Long customerId = 1L;
        Integer product1Count = 2;
        Integer product2Count = 4;
        Map<CustomerId, List<ProductCount>> results = new HashMap<>();
        results.put(new CustomerId(customerId), Arrays.asList(
                new ProductCount("Table", product1Count),
                new ProductCount("Chair", product2Count)));
        return results;
    }
    //</editor-fold>



    @Test
    void lackOfAbstractions() {
        Map<CustomerId, List<ProductCount>> map = extremeFP();

        for (CustomerId cid : map.keySet()) {
            String pl = map.get(cid).stream()
                    .map(t -> t.getCount() + " pcs. of " + t.getProductName())
                    .collect(joining(", "));
            System.out.println("cid=" + cid + " got " + pl);
        }
    }
}
@Value
class CustomerId{
    long id;
}

@Value
class ProductCount {
    String productName;
    int count;
}