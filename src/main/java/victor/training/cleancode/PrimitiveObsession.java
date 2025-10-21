package victor.training.cleancode;

import java.util.Map;

import static java.util.stream.Collectors.joining;
public class PrimitiveObsession {
  public static void main(String[] args) {
    new PrimitiveObsession().primitiveObsession(PaymentMethod.CARD);
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

  public void primitiveObsession(PaymentMethod paymentMethod) {
    if (paymentMethod != PaymentMethod.CARD && paymentMethod != PaymentMethod.CASH) {
      throw new IllegalArgumentException("Only CARD payment method is supported");
    }
//    String unString = "RO43BTRL124125149123182";// IBAN/SSN/BIC/ACCOUNT
    var customerIdToProductCounts = fetchData(paymentMethod);
//    var productCountsForCustomer = fetchData(paymentMethod);

    for (var e : customerIdToProductCounts.entrySet()) { // iterating map entries 🤢
      String pl = e.getValue().entrySet().stream()
          .map(entry -> entry.getValue() + " pcs. of " + entry.getKey())
          .collect(joining(", "));
      System.out.println("cid=" + e.getKey() + " got " + pl);
    }
  }

  enum PaymentMethod {
    CARD, CASH
  }

  //</editor-fold>
  record IBAN(String value) {} // #shoc!

  record CustomerId(long value) {}
}