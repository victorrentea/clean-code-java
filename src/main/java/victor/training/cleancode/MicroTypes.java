package victor.training.cleancode;

import org.junit.jupiter.api.Test;

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
  void lackOfAbstractions() {
    Map<Long, Map<String, Integer>> map = fetchData();
    // Joke: try "var" above 🤪

    for (Long cid : map.keySet()) {
      String pl = map.get(cid).entrySet().stream()
              .map(entry -> entry.getValue() + " pcs. of " + entry.getKey())
              .collect(joining(", "));
      System.out.println("cid=" + cid + " got " + pl);
    }
  }
}