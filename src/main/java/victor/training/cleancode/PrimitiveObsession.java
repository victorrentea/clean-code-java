package victor.training.cleancode;

import lombok.NonNull;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import static java.util.stream.Collectors.joining;
import static victor.training.cleancode.PaymentMethod.CARD;
import static victor.training.cleancode.PaymentMethod.CASH;

public class PrimitiveObsession {

  public static void main(String[] args) {
    new PrimitiveObsession().primitiveObsession(PaymentMethod.valueOf("CARD")); // at the entry of the data
    // response for 3rd party api, from your DB, from the user input

    // is this allowed? valid?
    // -> no=> fail ASAP
    new PrimitiveObsession().primitiveObsession(null);
  }

  //<editor-fold desc="fetchData()">
  public Map<CustomerId, Order> fetchData(PaymentMethod paymentMethod) {
    CustomerId customerId = new CustomerId(1L);
    Integer product1Count = 2;
    Integer product2Count = 4;
    return Map.of(customerId,new Order(
        List.of(new OrderLine("Table", product1Count),
        new OrderLine("Chair", product2Count))
    ));
  }
  //</editor-fold>

  record CustomerId(long id) { // semantic ID
  }
  record Order(List<OrderLine> lines) {}
  record OrderLine(String product, int count) {}

  // what are the possible values for the paymentMethod? CASH CARD PAY_ON_DELIVERY
  public void primitiveObsession(/*@NonNull */PaymentMethod paymentMethod) { // throws NPException at entry with null
//    if (!"CARD".equals(paymentMethod) && !"cash ".equals(paymentMethod)) { // Yoda notation ("literal".equals(variable)))
//    if (paymentMethod != CARD && paymentMethod != CASH) { // Yoda notation ("literal".equals(variable)))
    if (!paymentMethod.isOneOf(CARD,CASH)) { // Yoda notation ("literal".equals(variable)))
      throw new IllegalArgumentException("Only CARD or CASH payment method is supported");
    }
//    var map = fetchData(paymentMethod); // DON'T
//    Map<CustomerId, Map<String, Integer>> map = fetchData(paymentMethod);
//    Map<CustomerId, Map<String, Integer>> customerIdToProductCounts = fetchData(paymentMethod);
    Map<CustomerId, Order> map = fetchData(paymentMethod);

    for (var entry : map.entrySet()) { // iterating map entries 🤢
      String pl = entry.getValue().lines.stream()
          .map(orderLine -> orderLine.count() + " pcs. of " + orderLine.product())
          .collect(joining(", "));
      System.out.println("cid=" + entry.getKey().id() + " got " + pl);
    }
  }
}
enum PaymentMethod {
  CARD, CASH, PAY_ON_DELIVERY;

  public boolean isOneOf(PaymentMethod... paymentMethods) {
    return Arrays.stream(paymentMethods).anyMatch(paymentMethod -> this == paymentMethod);
  }
}