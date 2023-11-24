package victor.training.cleancode;

import com.google.common.collect.Maps;
import org.jooq.lambda.tuple.Tuple;
import org.jooq.lambda.tuple.Tuple2;
import org.junit.jupiter.api.Test;

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

  @Test
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
          .map(t -> t.v2 + " pcs. of " + t.v1)
          .collect(joining(", "));
      System.out.println("cid=" + cid + " got " + pl);
    }
  }
}