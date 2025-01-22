package victor.training.cleancode.java;

import java.util.List;
import java.util.Map;

import static java.util.stream.Collectors.joining;

public class PrimitiveObsessionVsTuples {

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

  record ProductCount(String name, int count) {
  }

  record CustomerId(long id) {
  } // OMG!! eg agentId

  void lackOfAbstractions() {
    Map<CustomerId, List<ProductCount>> map = extremeFP();

    for (CustomerId cid : map.keySet()) {
      String pl = map.get(cid).stream()
          .map(t -> t.count() + " pcs. of " + t.name())
          .collect(joining(", "));
      System.out.println("cid=" + cid.id() + " got " + pl);
    }
  }
}