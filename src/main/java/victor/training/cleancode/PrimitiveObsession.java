package victor.training.cleancode;

import java.util.Map;

import static java.util.stream.Collectors.joining;
import static victor.training.cleancode.PaymentMethod.CARD;
import static victor.training.cleancode.PaymentMethod.CASH;

enum PaymentMethod {
  CARD, CASH
}
public class PrimitiveObsession {
  public static void main(String[] args) {
    new PrimitiveObsession().f(CARD);// undeva la marginea cetatii, intr-un mapper, sau insusi Jackson cu niste flaguri
  }

  //<editor-fold desc="fetchData()">
  public Map<Long, Map<String, Integer>> fetchData(PaymentMethod paymentMethod) {
    Long customerId = 1L;
    Integer product1Count = 2;
    Integer product2Count = 4;
    return Map.of(customerId, Map.of(
        "Table", product1Count,
        "Chair", product2Count
    ));
  }
  //</editor-fold>

  public void f(PaymentMethod paymentMethod) {
    if (CARD != paymentMethod && paymentMethod != CASH) {
      throw new IllegalArgumentException("Only CARD or CASH payment method is supported");
    }
    Map<Long, Map<String, Integer>> customerToProductCounts = fetchData(paymentMethod);

    for (var entry1 : customerToProductCounts.entrySet()) { // iterating map entries 🤢
      String pl = entry1.getValue().entrySet().stream()
          .map(entry -> entry.getValue() + " pcs. of " + entry.getKey())
          .collect(joining(", "));
      System.out.println("cid=" + entry1.getKey() + " got " + pl);
    }
  }
}