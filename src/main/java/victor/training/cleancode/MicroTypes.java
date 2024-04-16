package victor.training.cleancode;

import java.util.List;
import java.util.Map;

import static java.util.stream.Collectors.joining;

public class MicroTypes {

  //<editor-fold desc="fetchData()">
  public Map<CustomerId, List<ProductCount>> fetchData() {
    CustomerId customerId = new CustomerId(1L);
    Integer product1Count = 2;
    Integer product2Count = 4;
    return Map.of(customerId, List.of(
        new ProductCount("Table", product1Count),
        new ProductCount("Chair", product2Count)
    ));
  }
  //</editor-fold>

  void primitiveObsession() {
//    Map<Long, Map<String, Integer>> customerIdToProductCounts = fetchData();
    Map<CustomerId, List<ProductCount>> customerIdToProductCounts = fetchData();

    for (var e : customerIdToProductCounts.entrySet()) {
      String pl = e.getValue().stream()
          .map(entry -> entry.product() + " pcs. of " + entry.count())
              .collect(joining(", "));
      System.out.println("cid=" + e.getKey().id() + " got " + pl);
    }
  }

  record CustomerId(Long id) {
  }

  record ProductCount(String product, Integer count) {
  }




}