package victor.training.cleancode;

import java.util.Map;

import static java.util.stream.Collectors.joining;

public class PrimitiveObsession {

  public static void main(String[] args) {
    new PrimitiveObsession().primitiveObsession("CARD");
  }

  //<editor-fold desc="fetchData()">
  public Map<CustomerId, ProductBasket> fetchData(String paymentMethod) {
    return Map.of(new CustomerId(1L), new ProductBasket(Map.of(
        "Table", 2,
        "Chair", 4
    )));
  }
  //</editor-fold>

  record CustomerId(long id) {} // microtype -> waiting for Valhalla project
//  record SWIFTCode(String code) {}
//  record IBAN(String code) {}

  record ProductBasket(Map<String, Integer> products) {
    void addProduct(String name, int count) {
      products.put(name, products.getOrDefault(name, 0) + count);
    }
  }

  public void primitiveObsession(String paymentMethod) {
    if (!"CARD".equals(paymentMethod) && !"CASH".equals(paymentMethod)) {
      throw new IllegalArgumentException("Only CARD payment method is supported");
    }
    // We adopted var 100% of the code.
    // We knew that that will remove meaning from types
    // But we covered it everywehere with better variable/method names
    Map<CustomerId, ProductBasket> productsPurchasedByCustomer = fetchData(paymentMethod);
//    Map<CustomerId, ProductBasket> productsPurchasedByCustomer = fetchData(paymentMethod);

    for (var e : productsPurchasedByCustomer.entrySet()) { // iterating map entries 🤢
//      String pl = e.getValue().entrySet().stream()
//          .map(entry -> entry.getValue() + " pcs. of " + entry.getKey())
//          .collect(joining(", "));
//      System.out.println("cid=" + e.getKey() + " got " + pl);

      String pl = e.getValue().products().entrySet().stream()
          .map(entry -> entry.getValue() + " pcs. of " + entry.getKey())
          .collect(joining(", "));
      System.out.println("cid=" + e.getKey().id() + " got " + pl);
    }
  }
}