package victor.training.cleancode;

import java.util.List;
import java.util.Map;

import static java.util.stream.Collectors.joining;
import static victor.training.cleancode.PrimitiveObsession.PaymentMethod.CARD;
import static victor.training.cleancode.PrimitiveObsession.PaymentMethod.CASH;

public class PrimitiveObsession {

  public static void main(String[] args) {
    new PrimitiveObsession().primitiveObsession(PaymentMethod.valueOf("CARD"));
  }
  // RO22BTRLEURCRT0311069601 = IBAN
  // BTRLRO22 = BIC/SWIFT
  // 0720019564
  // a@b.com
  // 1851124450056

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

  public void primitiveObsession(PaymentMethod paymentMethod) {
//    if (!PaymentMethod.CARD.equals(paymentMethod) && !PaymentMethod.CASH.equals(paymentMethod)) {
//    if (!paymentMethod.equals(PaymentMethod.CARD) && !paymentMethod.equals(PaymentMethod.CASH)) {
//    if (paymentMethod != PaymentMethod.CARD && paymentMethod != PaymentMethod.CASH) {
    if (!paymentMethod.oneOf(CARD, CASH)) {
      throw new IllegalArgumentException("Only CARD payment method is supported");
    }
    Map<CustomerId, List<ProductCount>> customerIdsToProductCounts = fetchData(paymentMethod);

    for (var e : customerIdsToProductCounts.entrySet()) { // iterating map entries 🤢
      String pl = e.getValue().stream()
          .map(entry -> entry.count() + " pcs. of " + entry.productName())
          .collect(joining(", "));
      System.out.println("cid=" + e.getKey() + " got " + pl);
    }
  }

  enum PaymentMethod {
    CARD("Card"),
    CASH("Cash"),
    CREDIT("Credit");

    private final String label;

    PaymentMethod(String label) {
      this.label = label;
    }

    public String getLabel() {
      return label;
    }

    public boolean oneOf(PaymentMethod... paymentMethods) {
      return List.of(paymentMethods).contains(this);
    }
  }

  record ProductCount(String productName, int count) {
  }

  record CustomerId(long id) {
  }
}