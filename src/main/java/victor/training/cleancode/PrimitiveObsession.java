package victor.training.cleancode;

import java.util.Arrays;
import java.util.Map;

import static java.util.stream.Collectors.joining;
import static victor.training.cleancode.PrimitiveObsession.PaymentMethod.CARD;
import static victor.training.cleancode.PrimitiveObsession.PaymentMethod.CASH;

public class PrimitiveObsession {
  public static void main(String[] args) {
    new PrimitiveObsession().entryPoint("CARD");
    new PrimitiveObsession().entryPoint("CArD");
    new PrimitiveObsession().entryPoint("CREDIT");
    new PrimitiveObsession().entryPoint("CARD ");
    new PrimitiveObsession().entryPoint(" ");
    new PrimitiveObsession().entryPoint(""); // SCARY
    new PrimitiveObsession().entryPoint(null); // SCARY
  }

  //<editor-fold desc="fetchData()">
  public Map<CustomerId, Map<String, Integer>> fetchData(PaymentMethod paymentMethod) {
    CustomerId customerId = new CustomerId(1L);
    Integer product1Count = 2;
    Integer product2Count = 4;
    return Map.of(customerId, Map.of(
        "Table", product1Count,
        "Chair", product2Count
    ));
  }

  public void entryPoint(String paymentMethod) {// called with String, JSON.XML,,....
    foo(PaymentMethod.valueOf(paymentMethod)); // conversion aka mapper/transformer
  }
  //</editor-fold>

  public void foo(PaymentMethod paymentMethod) {
//    if (!paymentMethod.equals("CARD") && !"CASH".equals(paymentMethod)) {// BAD
//    if (!"CARD".equals(paymentMethod) && !"CASH".equals(paymentMethod)) { // NULL-DRIVEN DEVELOPMENT
//    if (paymentMethod != PaymentMethod.CARD && paymentMethod != PaymentMethod.CASH) {
    if (!paymentMethod.oneOf(CARD, CASH)) {
      throw new IllegalArgumentException("Only CARD or CASH payment method is supported");
    }
    Map<CustomerId, Map<String, Integer>> customerIdToProductCounts = fetchData(paymentMethod);

    for (var e : customerIdToProductCounts.entrySet()) { // iterating map entries 🤢
      String pl = e.getValue().entrySet().stream()
          .map(entry -> entry.getValue() + " pcs. of " + entry.getKey())
          .collect(joining(", "));
      System.out.println("cid=" + e.getKey().id() + " got " + pl);
    }
  }

  enum PaymentMethod {
    CARD, CASH;

    public boolean oneOf(PaymentMethod... values) {
      return Arrays.stream(values).anyMatch(value -> value.equals(this));
    }
  }

  record CustomerId(long id) {
  }
}