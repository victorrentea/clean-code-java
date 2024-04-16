package victor.training.cleancode;

import java.util.List;
import java.util.Map;

import static java.util.stream.Collectors.joining;

public class MicroTypes {

  //<editor-fold desc="fetchData()">
  public Map<Long, List<ProductCount>> fetchData() {
    Long customerId = 1L;
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
    Map<Long, List<ProductCount>> customerIdToProductCounts = fetchData();

    for (var e : customerIdToProductCounts.entrySet()) {
      String pl = e.getValue().stream()
          .map(entry -> entry.product() + " pcs. of " + entry.count())
              .collect(joining(", "));
      System.out.println("cid=" + e.getKey() + " got " + pl);
    }
  }

  record ProductCount(String product, Integer count) {
  }




}