package victor.training.cleancode;

import lombok.Data;
import lombok.Value;
import org.jooq.lambda.tuple.Tuple;
import org.jooq.lambda.tuple.Tuple2;
import org.junit.jupiter.api.Test;
import victor.training.cleancode.exception.model.Customer;

import java.io.Serializable;
import java.lang.ref.WeakReference;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.WeakHashMap;

import static java.util.stream.Collectors.joining;


//type CustomerId = long
//@Embeddable
@Data
class CustomerId implements Serializable {
    private final long id;

//    private static final WeakHashMap<Long, WeakReference<CustomerID>>
//    public static void of() {
//
//    }


    public CustomerId(long id) {
        this.id = id;
    }

    public long id() {
        return id;
    }
}
@Value
class ProductCount {
    String productName;
    int itemCount;
}
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

    @Test
    void lackOfAbstractions() {
        Map<CustomerId, List<ProductCount>> map = extremeFP();
        // Joke: try "var" above :)

        String x;
        if (map.isEmpty()) {
            System.out.println("COmmont");
            x = "bla";
        } else {
            System.out.println("COmmont");
            x = "wawsa";
        }
        System.out.println(x);

        for (CustomerId cid : map.keySet()) {
            String pl = map.get(cid).stream()
                    .map(t -> t.getProductName() + " of " + t.getItemCount())
                    .collect(joining(", "));
            System.out.println("cid=" + cid + " got " + pl);
        }
    }
}