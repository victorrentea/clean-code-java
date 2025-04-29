package victor.training.cleancode;

import java.util.List;
import java.util.Map;

import static java.util.stream.Collectors.joining;

public class PrimitiveObsessionVsTuples {

  //<editor-fold desc="Tuple source of data">
  public Map<CustomerId, List<CartItem>> extremeFP() {
    Long customerId = 1L;
    Integer product1Count = 2;
    Integer product2Count = 4;
    return Map.of(new CustomerId(customerId), List.of(
        new CartItem("Table", product1Count),
        new CartItem("Chair", product2Count)
    ));
  }
  //</editor-fold>

  void lackOfAbstractions() {
//    Map<CustomerId, List<CartItem>> map = extremeFP();
    var customerCartItems = extremeFP();
//    Map<CustomerId, List<CartItem>> map = extremeFP();
    // --- hides types
    // + less work editing the code
    // + more js-style
    for (CustomerId customerId : customerCartItems.keySet()) {
      String pl = customerCartItems.get(customerId).stream()
          .map(cartItem -> cartItem.numberOfItems() + " pcs. of " + cartItem.productName())
          .collect(joining(", "));
      System.out.println("cid=" + customerId + " got " + pl);
    }
  }

  record CartItem(String productName, int numberOfItems) {
  }

  record CustomerId(long id) {
  } // microtypes for critical
}