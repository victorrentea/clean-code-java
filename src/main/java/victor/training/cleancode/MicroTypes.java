package victor.training.cleancode;

import lombok.Value;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;

import static java.util.stream.Collectors.joining;

public class MicroTypes {

  //<editor-fold desc="fetchData()">
  public Map<CustomerId, List<ProductCount>> fetchData() {
    Long customerId = 1L;
    return Map.of(new CustomerId(customerId), List.of(
            new ProductCount("Table", 2),
            new ProductCount("Chair", 4)
    ));
  }
  //</editor-fold>

  @Value
  class CustomerId {long customerId;}
  @Value
  class ProductCount {
    String productName;
    int count;
  }

  @Test
  void lackOfAbstractions() {
    Map<CustomerId, List<ProductCount>> productCounts = fetchData();
    // Joke: try "var" above 🤪

    for (CustomerId cid : productCounts.keySet()) {
      String pl = productCounts.get(cid).stream()
              .map(pc -> pc.getCount() + " pcs. of " + pc.getProductName())
              .collect(joining(", "));
      System.out.println("cid=" + cid + " got " + pl);
    }
  }
}