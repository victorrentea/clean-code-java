package victor.training.cleancode;

import java.util.Map;

import static java.util.stream.Collectors.joining;

public class PrimitiveObsession {

  public static void main(String[] args) {
    new PrimitiveObsession().primitiveObsession("CARD");
  }

  //<editor-fold desc="fetchData()">
  public Map<CustomerId, Map<String, Integer>> fetchData(String paymentMethod) {
    Long customerId = 1L;
    Integer product1Count = 2;
    Integer product2Count = 4;
    return Map.of(new CustomerId(customerId), Map.of(
        "Table", product1Count,
        "Chair", product2Count
    ));
  }
  //</editor-fold>

  record OrderId(long id) {}

  record CustomerId(long id) {}

  record IBAN(String value) {}

  record BIC(String bic) {}

  public void primitiveObsession(String paymentMethod) {
    if (!"CARD".equals(paymentMethod) && !"CASH".equals(paymentMethod)) {
      throw new IllegalArgumentException("Only CARD payment method is supported");
    }
    // IBAN{RO45BTRL..}, BIC/SWIFT, CNP {1851124450056}
    Map<CustomerId, Map<String, Integer>> productQuantitiesPerCustomer = fetchData(paymentMethod);
//    Map<CustomerId, List<ProductCount>> productQuantitiesPerCustomer = fetchData(paymentMethod);

    for (var e : productQuantitiesPerCustomer.entrySet()) { // iterating map entries 🤢
      String pl = e.getValue().entrySet().stream()
          .map(entry -> entry.getValue() + " pcs. of " + entry.getKey())
          .collect(joining(", "));
      System.out.println("cid=" + e.getKey().id + " got " + pl);
    }
  }
}