package victor.training.cleancode;

import java.util.List;
import java.util.Map;

import static java.util.stream.Collectors.joining;
import static victor.training.cleancode.PrimitiveObsession.PaymentMethod.*;

public class PrimitiveObsession {

  public static void main(String[] args) {
    new PrimitiveObsession().primitiveObsession(
        valueOfIgnoringCase("CARD"));
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
  //</editor-fold>

  // class Recording{ UUID/String/Long id; }
  // 10K "String recordingId","rId", "id", "recId"
  // String agentId, userId, agentUUID;

  public void primitiveObsession(PaymentMethod paymentMethod) {
//    if (paymentMethod != CARD && paymentMethod != CASH) {
//    if (!(paymentMethod == CARD || paymentMethod == CASH)) {
    if (!paymentMethod.oneOf(CARD, CASH)) {
      throw new IllegalArgumentException("Only CARD payment method is supported");
    }
    Map<CustomerId, Map<String, Integer>> map = fetchData(paymentMethod);

    for (var e : map.entrySet()) { // iterating map entries 🤢
      String pl = e.getValue().entrySet().stream()
          .map(entry -> entry.getValue() + " pcs. of " + entry.getKey())
          .collect(joining(", "));
      CustomerId id = e.getKey();
      System.out.println("cid=" + id.id + " got " + pl);
    }
  }
  enum PaymentMethod {
    CARD, CASH, CREDIT, PAYPAL, UNKNOWN;

    public static PaymentMethod valueOfIgnoringCase(String s) {
      return valueOf(s.toUpperCase().trim());
    }

    public boolean oneOf(PaymentMethod... options) {
      return List.of(options).contains(this);
    }
  }

  record CustomerId(long id) {
  }
}