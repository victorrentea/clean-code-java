package victor.training.cleancode;

import com.google.common.collect.Maps;
import org.junit.jupiter.api.Test;

import java.util.Collection;
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

    for (var e : map.entrySet()) {
      String pl = e.getValue().entrySet().stream()
              .map(entry -> entry.getValue() + " pcs. of " + entry.getKey())
              .collect(joining(", "));
      System.out.println("cid=" + e.getKey() + " got " + pl);
    }
  }

//  @Test
//  void tuples() {
//    Map<Long, Map<String, Integer>> map = fetchData().;
//
//    for (var e : map.entrySet()) {
//      String pl = e.getValue().entrySet().stream()
//          .map(entry -> entry.getValue() + " pcs. of " + entry.getKey())
//          .collect(joining(", "));
//      System.out.println("cid=" + e.getKey() + " got " + pl);
//    }
//  }
}