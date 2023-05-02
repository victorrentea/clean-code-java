package victor.training.cleancode;

import lombok.NonNull;
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
    long value;
  }

  @Value
  class ProductCount {
    @NonNull // adauga un if in constructor care arunca exceptie daca incerci sa faci new ProductCount(null, ...)
    String productName;
    int count;
  }

  @Test
  void lackOfAbstractions() {
    Map<CustomerId, List<ProductCount>> map = fetchData();
    // Joke: try "var" above 🤪

    for (CustomerId customerId : map.keySet()) {
      String pl = map.get(customerId).stream()
              .map(pc -> pc.getCount() + " pcs. of " + pc.getProductName())
              .collect(joining(", "));
      System.out.println("cid=" + customerId.getValue() + " got " + pl);
    }
  }
}