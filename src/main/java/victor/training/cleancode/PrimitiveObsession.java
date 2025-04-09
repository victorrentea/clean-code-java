package victor.training.cleancode;

import java.util.List;
import java.util.Map;

import static java.util.stream.Collectors.joining;
import static victor.training.cleancode.PrimitiveObsession.PaymentMethod.CARD;
import static victor.training.cleancode.PrimitiveObsession.PaymentMethod.CASH;

public class PrimitiveObsession {

  public static void main(String[] args) {
    new PrimitiveObsession().primitiveObsession(CARD);
  }

  //<editor-fold desc="fetchData()">
  public Map<CustomerId, List<ProductCount>> fetchData(PaymentMethod paymentMethod) {
    var customerId = new CustomerId(1L);
    Integer product1Count = 2;
    Integer product2Count = 4;
    return Map.of(customerId, List.of(
        new ProductCount("Table", product1Count),
        new ProductCount("Chair", product2Count)
    ));
  }
  //</editor-fold>

  enum PaymentMethod {
    CARD, CASH, BLOOD
  }
  record ProductCount(String productName, int count) {
  }
  record /*struct*/ CustomerId(long id) {
  }
  // IBAN, CNP, SSN, VATCode/CUI, Email ...
  public void primitiveObsession(PaymentMethod paymentMethod) {
    if (paymentMethod != CARD && paymentMethod != CASH) {
      throw new IllegalArgumentException("Only CARD payment method is supported");
    }
    Map<CustomerId, List<ProductCount>> map = fetchData(paymentMethod);

    for (var e : map.entrySet()) { // iterating map entries 🤢
      String pl = e.getValue().stream()
          .map(pc -> pc.count() + " pcs. of " + pc.productName())
          .collect(joining(", "));
      System.out.println("cid=" + e.getKey().id + " got " + pl);
    }
  }
}