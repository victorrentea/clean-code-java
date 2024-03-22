package victor.training.cleancode;

import com.google.common.collect.Maps;
import org.jooq.lambda.tuple.Tuple;
import org.jooq.lambda.tuple.Tuple2;

import java.util.Collection;
import java.util.List;
import java.util.Map;

import static java.util.stream.Collectors.joining;

public class MicroTypes {

  //<editor-fold desc="fetchData()">
  public Map<Long, Map<String, Integer>> fetchData() {
    Long customerId = 1L;
    Integer product1Count = 2;
    Integer product2Count = 4;
    return Map.of(customerId, Map.of(
            "Table", product1Count,
            "Chair", product2Count
    ));
  }
  //</editor-fold>

  void primitiveObsession() {
    Map<Long, Map<String, Integer>> map = fetchData();

    for (var e : map.entrySet()) { // iterating map entries 🤢
      String pl = e.getValue().entrySet().stream()
              .map(entry -> entry.getValue() + " pcs. of " + entry.getKey())
              .collect(joining(", "));
      System.out.println("cid=" + e.getKey() + " got " + pl);
    }
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