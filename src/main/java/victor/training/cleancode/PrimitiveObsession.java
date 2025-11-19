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

  public void primitiveObsession(String paymentMethod) {
    if (!"CARD".equals(paymentMethod) && !"CASH".equals(paymentMethod)) {
      throw new IllegalArgumentException("Only CARD payment method is supported");
    }
    Map<CustomerId, Map<String, Integer>> map = fetchData(paymentMethod);

    for (var e : map.entrySet()) { // iterating map entries 🤢
      String pl = e.getValue().entrySet().stream()
          .map(entry -> entry.getValue() + " pcs. of " + entry.getKey())
          .collect(joining(", "));
      System.out.println("cid=" + e.getKey().id() + " got " + pl);
    }
  }
//  record CNP(String value) {}
//  record CodClient(String value) {}
//  record CodPunctConsum(String value) {}
//  record CodPlata(String value) {}
//  record CodFacutr(String value) {}

  record CustomerId(long id) {}
}