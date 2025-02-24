package victor.training.cleancode;

import java.util.List;
import java.util.Map;

import static java.util.stream.Collectors.joining;
import static victor.training.cleancode.PrimitiveObsession.PaymentMethod.CARD;
import static victor.training.cleancode.PrimitiveObsession.PaymentMethod.CASH;

public class PrimitiveObsession {

  public static void main(String[] args) {
    String fromJson = "CARD";
    // sanitize & validate
    PaymentMethod paymentMethod = PaymentMethod.valueOf(fromJson.trim().toUpperCase());
    new PrimitiveObsession().primitiveObsession(paymentMethod);
  }

  //<editor-fold desc="fetchData()">
  public Map<CustomerId, Map<String, Integer>> fetchData(PaymentMethod paymentMethod) {
    var customerId = new CustomerId(1L);
    Integer product1Count = 2;
    Integer product2Count = 4;
    return Map.of(customerId, Map.of(
        "Table", product1Count,
        "Chair", product2Count
    ));
  }
  //</editor-fold>

  public void primitiveObsession(PaymentMethod paymentMethod) {
//    if (!PaymentMethod.CARD.equals(paymentMethod) && !PaymentMethod.CASH.equals(paymentMethod)) {
//    if (paymentMethod != CARD && paymentMethod != CASH) {
//    if (!isOneOf(paymentMethod, CASH, CARD)) {
//    if (!ALLWED_PAYMENT_METHODS.contains(paymentMethod)) {
    if (!paymentMethod.isOneOf(CASH, CARD)) {
      throw new IllegalArgumentException("Only CARD or CASH payment method is supported");
    }
//    var map = fetchData(paymentMethod);
    Map<CustomerId, Map<String, Integer>> map = fetchData(paymentMethod);

    for (var e : map.entrySet()) { // iterating map entries 🤢
      String pl = e.getValue().entrySet().stream()
          .map(entry -> entry.getValue() + " pcs. of " + entry.getKey())
          .collect(joining(", "));
      System.out.println("cid=" + e.getKey().id() + " got " + pl);
    }
  }

  enum PaymentMethod {
    CARD, CASH, BLOOD, SHEEP;

    public boolean isOneOf(PaymentMethod... allowed) {
      //    return paymentMethod != CARD && paymentMethod != CASH;
      return List.of(allowed).contains(this);
    }
  }

  // microtypes
  record CustomerId(long id) {
  } // PANIC !! WTF! WHY>?! to give semantics to numbers

  record SSN(String values) {
  }

}