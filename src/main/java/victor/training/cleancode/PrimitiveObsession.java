package victor.training.cleancode;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import static java.util.stream.Collectors.joining;
import static victor.training.cleancode.PaymentMethod.CARD;
import static victor.training.cleancode.PaymentMethod.CASH;

enum PaymentMethod {
  CARD, CASH;

  public boolean oneOf(PaymentMethod... args) {
    return Arrays.asList(args).contains(this);
  }
}
public class PrimitiveObsession {
  public static void main(String[] args) {
    new PrimitiveObsession().f(CARD);// undeva la marginea cetatii, intr-un mapper, sau insusi Jackson cu niste flaguri
  }

  //<editor-fold desc="fetchData()">
  public Map<CustomerId, List<ProductCount>> fetchData(PaymentMethod paymentMethod) {
    Long customerId = 1L;
    Integer product1Count = 2;
    Integer product2Count = 4;
    return Map.of(new CustomerId(customerId), List.of(
        new ProductCount("Table", product1Count),
        new ProductCount("Chair", product2Count)
    ));
  }
  //</editor-fold>

  public void f(PaymentMethod paymentMethod) {
    if (!paymentMethod.oneOf(CARD, CASH)) {
      throw new IllegalArgumentException("Only CARD or CASH payment method is supported");
    }
    // exercitiu pt cititor
//    paymentMethod.checkOneOf(CARD, CASH);
    Map<CustomerId, List<ProductCount>> customerToProductCounts = fetchData(paymentMethod);

    for (var entry1 : customerToProductCounts.entrySet()) { // iterating map entries 🤢
      String pl = entry1.getValue().stream()
          .map(productCount -> productCount.count() + " pcs. of " + productCount.productName())
          .collect(joining(", "));
      System.out.println("cid=" + entry1.getKey().id() + " got " + pl);
    }
  }

  record CustomerId(long id) {
  }

  record ProductCount(String productName, int count) {
  }
}