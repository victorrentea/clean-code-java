package victor.training.cleancode;

import org.jooq.lambda.tuple.Tuple;
import org.jooq.lambda.tuple.Tuple2;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;

import static java.util.stream.Collectors.joining;

public class MicroTypes {

    record CustomerId(long id){}
    record ProductLine(String productName, int count){}

    //<editor-fold desc="Unknown source of data">
    public Map<CustomerId, List<ProductLine>> extremeFP() {
        Long customerId = 1L;
        Integer product1Count = 2;
        Integer product2Count = 4;
        return Map.of(new CustomerId(customerId), List.of(
                new ProductLine("Table", product1Count),
                new ProductLine("Chair", product2Count
                )));
    }
    //</editor-fold>

    @Test
    void lackOfAbstractions() {
        Map<CustomerId, List<ProductLine>> map = extremeFP();

        for (CustomerId cid : map.keySet()) {
            String s = map.get(cid).stream()
                    .map(pl -> pl.count() + " pcs. of " + pl.productName())
                    .collect(joining(", "));
            System.out.println("cid=" + cid.id + " got " + s);
        }
    }
}