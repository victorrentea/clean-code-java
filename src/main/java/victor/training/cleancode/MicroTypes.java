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
    Integer product1Count = 2;
    Integer product2Count = 4;
    return Map.of(new CustomerId(customerId), List.of(
            new ProductCount("Table", product1Count),
            new ProductCount("Chair", product2Count)
    ));
  }
  //</editor-fold>
  @Value
  class CustomerId {
    long id;
  }

  @Value
  class ProductCount {
    String productName;
    int count;
  }


  @Test
  void lackOfAbstractions() {
    Map<CustomerId, List<ProductCount>> customerToProductCounts = fetchData();
    // Joke: try "var" above 🤪

    for (CustomerId cid : customerToProductCounts.keySet()) {
      String pl = customerToProductCounts.get(cid).stream()
              .map(entry -> entry.getCount() + " pcs. of " + entry.getProductName())
              .collect(joining(", "));
      System.out.println("cid=" + cid.id + " got " + pl);
    }
  }
}