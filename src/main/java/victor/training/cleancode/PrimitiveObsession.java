package victor.training.cleancode;

import lombok.extern.slf4j.Slf4j;

import java.util.Map;

import static java.util.stream.Collectors.joining;
import static victor.training.cleancode.PrimitiveObsession.PaymentMethod.CARD;

@Slf4j
public class PrimitiveObsession {

  public static void main(final String[] args) {
    final String inputu = "CARD";
    new PrimitiveObsession().primitiveObsession(PaymentMethod.valueOf(inputu));
  }

  //<editor-fold desc="fetchData()">
  public Map<CustomerId, Map<String, Integer>> fetchData(final PaymentMethod paymentMethod) {
    final Long customerId = 1L;
    final Integer product1Count = 2;
    final Integer product2Count = 4;
    return Map.of(new CustomerId(customerId), Map.of(
        "Table", product1Count,
        "Chair", product2Count
    ));
  }
  //</editor-fold>

  // IBAN, SWIFT, CNP, Email, SSN
  public void primitiveObsession(final PaymentMethod paymentMethod) {
//    if (!"CARD".equalsIgnoreCase(paymentMethod.trim()) && !"CASH".equals(paymentMethod)) {
//    if (paymentMethod != CARD && !PaymentMethod.CASH.equals(paymentMethod)) {
    if (paymentMethod != CARD && PaymentMethod.CASH != paymentMethod) {
      throw new IllegalArgumentException("Only CARD payment method is supported");
    }
    final Map<CustomerId, Map<String, Integer>> map = fetchData(paymentMethod);

    for (final var e : map.entrySet()) { // iterating map entries 🤢
      // FATAL: OOME (moare procesu)
      // ERROR WARN:moare curand/corupi date
      // INFO: 1-2 linii / request << la nivelul asta deployezi
      // DEBUG: cand si alt suflet cauta un bug pe-aici
      // TRACE: intr-un for
      log.trace("tot ce voiam sa vad la breakpoint: {} -> {}", e.getKey(), e.getValue());
      final String pl = e.getValue().entrySet().stream()
          .map(entry -> entry.getValue() + " pcs. of " + entry.getKey())
          .collect(joining(", "));
      System.out.println("cid=" + e.getKey() + " got " + pl);
    }
  }

  enum PaymentMethod {
    CARD, CASH
  }

  record CustomerId(Long id) {}
}