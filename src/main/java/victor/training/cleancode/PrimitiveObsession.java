package victor.training.cleancode;

import java.util.Map;

import static java.util.stream.Collectors.joining;

public class PrimitiveObsession {
  public static void main(String[] args) {
    new PrimitiveObsession().f("CARD");
  }

  //<editor-fold desc="fetchData()">
  public Map<Long, Map<String, Integer>> fetchData(String paymentMethod) {
    Long customerId = 1L;
    Integer product1Count = 2;
    Integer product2Count = 4;
    return Map.of(customerId, Map.of(
        "Table", product1Count,
        "Chair", product2Count
    ));
  }
  //</editor-fold>

  public void f(String paymentMethod) {
    if (!"CARD".equals(paymentMethod) && !"CASH".equals(paymentMethod)) {
      throw new IllegalArgumentException("Only CARD or CASH payment method is supported");
    }
    Map<Long, Map<String, Integer>> customerToProductCounts = fetchData(paymentMethod);

    for (var e : customerToProductCounts.entrySet()) { // iterating map entries 🤢
      String pl = e.getValue().entrySet().stream()
          .map(entry -> entry.getValue() + " pcs. of " + entry.getKey())
          .collect(joining(", "));
      System.out.println("cid=" + e.getKey() + " got " + pl);
    }
  }
}