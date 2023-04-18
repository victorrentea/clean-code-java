package victor.training.cleancode;

import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.joining;
import static java.util.stream.Collectors.toMap;

public class MicroTypes {

  private static String createSummary(Entry<CustomerId, List<ProductCount>> e) {
    return e.getValue().stream()
            .map(entry -> entry.count() + " pcs. of " + entry.productName())
            .collect(joining(", "));
  }
  //</editor-fold>

  //<editor-fold desc="Unknown source of data">
  public Map<CustomerId, List<ProductCount>> extremeFP() {
    Long customerId = 1L;
    Integer product1Count = 2;
    Integer product2Count = 4;
    return Map.of(new CustomerId(customerId), List.of(
            new ProductCount("Table", product1Count),
            new ProductCount("Chair", product2Count)
    ));
  }

  @Test
  void lackOfAbstractions() {
    Map<CustomerId, List<ProductCount>> map = extremeFP();
    // Joke: try "var" above :)

    Map<CustomerId, String> productSummaries = map.entrySet().stream()
            .collect(toMap(Entry::getKey, MicroTypes::createSummary));

    productSummaries.forEach((cid, summ) -> System.out.println("cid=" + cid + " got " + summ));

    //    for (CustomerId cid : map.keySet()) {
    //      String productSummary = map.get(cid).stream()
    //              .map(entry -> entry.count() + " pcs. of " + entry.productName())
    //              .collect(joining(", "));
    //      System.out.println("cid=" + cid + " got " + productSummary);
    //    }
  }

  record CustomerId(long id) {
  }

  record ProductCount(String productName, int count) {
  }
}