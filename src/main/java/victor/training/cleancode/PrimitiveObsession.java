package victor.training.cleancode;

import java.util.List;
import java.util.Map;

import static java.util.stream.Collectors.joining;
import static victor.training.cleancode.PrimitiveObsession.PaymentMethod.*;

public class PrimitiveObsession {

  public static void main(String[] args) {
    new PrimitiveObsession().primitiveObsession(
        valueOfIgnoringCase("CARD"));
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

  public void primitiveObsession(PaymentMethod paymentMethod) {
//    if (paymentMethod != CARD && paymentMethod != CASH) {
//    if (!(paymentMethod == CARD || paymentMethod == CASH)) {
    if (!(paymentMethod.oneOf(CARD, CASH))) {
      throw new IllegalArgumentException("Only CARD payment method is supported");
    }
    Map<Long, Map<String, Integer>> map = fetchData(paymentMethod);

    for (var e : map.entrySet()) { // iterating map entries 🤢
      String pl = e.getValue().entrySet().stream()
          .map(entry -> entry.getValue() + " pcs. of " + entry.getKey())
          .collect(joining(", "));
      System.out.println("cid=" + e.getKey() + " got " + pl);
    }
  }

  enum PaymentMethod {
    CARD, CASH;

    public static PaymentMethod valueOfIgnoringCase(String s) {
      return valueOf(s.toUpperCase());
    }

    public boolean oneOf(PaymentMethod... options) {
      return List.of(options).contains(this);
    }
  }
}