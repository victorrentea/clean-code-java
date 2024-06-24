package victor.training.cleancode;

import java.util.List;
import java.util.Map;

import static java.util.stream.Collectors.joining;

public class PrimitiveObsession {

  public static void main(String[] args) {
    new PrimitiveObsession().primitiveObsession("CARD");
  }

  //<editor-fold desc="fetchData()">
  public Map<CustomerId, Order> fetchData(String paymentMethod) {
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

  public void primitiveObsession(String paymentMethod) {
    if (!"CARD".equals(paymentMethod) && !"CASH".equals(paymentMethod)) { // Yoda notation ("literal".equals(variable)))
      throw new IllegalArgumentException("Only CARD payment method is supported");
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