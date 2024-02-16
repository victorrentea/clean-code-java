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

  record CustomerId(Long id) {}
  private record ProductCount(String name, Integer count) {}

  void primitiveObsession() {
    Map<CustomerId, List<ProductCount>> map = fetchData();

    for (var e : map.entrySet()) { // iterating map entries 🤢
      String pl = e.getValue().stream()
              .map(productCount -> productCount.count() + " pcs. of " + productCount.name)
              .collect(joining(", "));
      System.out.println("cid=" + e.getKey().id() + " got " + pl);
    }
  }






  //<editor-fold desc="Tuple source of data">
  public Map<Long, List<Tuple2<String, Integer>>> extremeFP() {
    Long customerId = 1L;
    Integer product1Count = 2;
    Integer product2Count = 4;
    return Map.of(customerId, List.of(
        org.jooq.lambda.tuple.Tuple.tuple("Table", product1Count),
        Tuple.tuple("Chair", product2Count)
    ));
  }
  //</editor-fold>

  void lackOfAbstractions() {
    Map<Long, List<Tuple2<String, Integer>>> map = extremeFP();
    // Joke: try "var" above :)

    for (Long cid : map.keySet()) {
      String pl = map.get(cid).stream()
          .map(t -> t.v2 + " pcs. of " + t.v1)
          .collect(joining(", "));
      System.out.println("cid=" + cid + " got " + pl);
    }
  }
}