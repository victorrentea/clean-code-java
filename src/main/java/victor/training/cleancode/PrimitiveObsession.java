package victor.training.cleancode;

import java.util.EnumSet;
import java.util.List;
import java.util.Map;

import static java.util.stream.Collectors.joining;
import static victor.training.cleancode.PrimitiveObsession.PaymentMethod.CARD;
import static victor.training.cleancode.PrimitiveObsession.PaymentMethod.CASH;

public class PrimitiveObsession {

  public static void main(String[] args) {
    new PrimitiveObsession().primitiveObsession(PaymentMethod.valueOf("CARD"));
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
//  public void primitiveObsession(String paymentMethod) {
//    if (paymentMethod != CARD && paymentMethod != CASH) {
//    if (paymentMethod.notOneOf(CARD, CASH)) {
    if (!EnumSet.of(CARD, CASH).contains(paymentMethod)) {
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
    CARD, CASH, BLOOD;

    public boolean notOneOf(PaymentMethod... methods) {
//      for (PaymentMethod method : methods) {
//        if (this == method) {
//          throw new IllegalArgumentException("Only " + Arrays.toString(methods) + "  payment method is supported");
//        }
//      }
//      Arrays.stream(methods)
//          .filter(method -> this == method)
//          .findFirst()
//          .ifPresent(method -> {
//            throw new IllegalArgumentException("Only " + Arrays.toString(methods) + " payment method is supported");
//          });

      return !List.of(methods).contains(this);
    }
  }
}