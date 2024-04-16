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






  //<editor-fold desc="Tuple source of data">
  public Map<CustomerId, List<ProductCount>> extremeFP() {
    Long customerId = 1L;
    Integer product1Count = 2;
    Integer product2Count = 4;
    return Map.of(new CustomerId(customerId), List.of(
        new ProductCount("Table", product1Count),
        new ProductCount("Chair", product2Count)
    ));
  }
  //</editor-fold>

  record CustomerId(Long id) {}
  // other examples:
  // OK: IBAN(String), SatelliteId(Long), ProductCode(String), OrderId(Long), etc.
  // NOK: TransactionId(String), 20GB/day of XML 1M VISA transactions today
  // PERFORMANCE HIT: memory waste until Project Valhalla (Java ????)
  // Kotlin: inline class
  record ProductCount(String product, Integer count) {}

  void lackOfAbstractions() {
//    var map = extremeFP();// BAD!!
    Map<CustomerId, List<ProductCount>> customerIdToProductCounts = extremeFP();
    // Joke: try "var" above :)

    for (CustomerId cid : customerIdToProductCounts.keySet()) {
      String pl = customerIdToProductCounts.get(cid).stream()
          .map(productCount -> productCount.count() + " pcs. of " + productCount.product())
          .collect(joining(", "));
      System.out.println("cid=" + cid.id() + " got " + pl);
    }
  }
}