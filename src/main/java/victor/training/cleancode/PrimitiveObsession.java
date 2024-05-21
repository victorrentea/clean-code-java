package victor.training.cleancode;

import java.util.Map;

import static java.util.stream.Collectors.joining;
import static victor.training.cleancode.PaymentMethod.*;

public class PrimitiveObsession {

  public static void main(String[] args) {
    new PrimitiveObsession().primitiveObsession(valueOf("CARD"));
  }

  //<editor-fold desc="fetchData()">
  public Map<CustomerId, Map<String, Integer>> fetchData(PaymentMethod paymentMethod) {
    Long customerId = 1L;
    Integer product1Count = 2;
    Integer product2Count = 4;
    return Map.of(new CustomerId(customerId), Map.of(
        "Table", product1Count,
        "Chair", product2Count
    ));
  }
  //</editor-fold>

  record CustomerId(Long id) {
  }
  public void primitiveObsession(PaymentMethod paymentMethod) {
    if (!paymentMethod.isOneOf(CARD, CASH)) {
      throw new IllegalArgumentException("Only CARD and CASH payment method is supported");
    }
    Map<CustomerId, Map<String, Integer>> map = fetchData(paymentMethod);

    for (var e : map.entrySet()) { // iterating map entries 🤢
      String pl = e.getValue().entrySet().stream()
          .map(entry -> entry.getValue() + " pcs. of " + entry.getKey())
          .collect(joining(", "));
      System.out.println("cid=" + e.getKey().id() + " got " + pl);
    }
  }
}