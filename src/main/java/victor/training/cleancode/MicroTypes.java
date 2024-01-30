package victor.training.cleancode;

import com.google.common.collect.Maps;
import lombok.NonNull;
import org.jooq.lambda.tuple.Tuple;
import org.jooq.lambda.tuple.Tuple2;
import org.junit.jupiter.api.Test;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import static java.util.stream.Collectors.joining;

public class MicroTypes {

  //<editor-fold desc="fetchData()">
  public Map<CustomerId,List<ProductCount>> fetchData() {
    Long customerId = 1L;
    Integer product1Count = 2;
    Integer product2Count = 4;
    return Map.of(new CustomerId(customerId),
        List.of(
            new ProductCount("Table", product1Count),
            new ProductCount("Chair", product2Count)
    ));
  }
  //</editor-fold>

//  "Swift"
  record SwiftCode(String s){}
  record CustomerId(Long id) {}
  record ProductCount(@NonNull String name, int count) {}

//  record Look(String name, Optional<String> phone) {}

  public static void main(String[] args) {
    new ProductCount(null, 2);
  }
  @Test
  void primitiveObsession() {
    Map<CustomerId, List<ProductCount>> map = fetchData();

    for (var e : map.entrySet()) { // iterating map entries 🤢
      String pl = e.getValue().stream()
              .map(entry -> entry.count() + " pcs. of " + entry.name())
              .collect(joining(", "));
      System.out.println("cid=" + e.getKey().id + " got " + pl);
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

  @Test
  void lackOfAbstractions() {
    Map<Long, List<Tuple2<String, Integer>>> map = extremeFP();
    // Joke: try "var" above :)

    for (Long cid : map.keySet()) {
      String pl = map.get(cid).stream()
          .map(t -> {
            Integer count = t.v2;
            return count + " pcs. of " + t.v1;
          })
          .collect(joining(", "));
      System.out.println("cid=" + cid + " got " + pl);
    }
  }
}