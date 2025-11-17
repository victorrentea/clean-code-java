package victor.training.cleancode.fp;

import lombok.RequiredArgsConstructor;
import victor.training.cleancode.fp.support.Order;
import victor.training.cleancode.fp.support.PaymentCardDto;
import victor.training.cleancode.fp.support.PaymentCardMapper;
import victor.training.cleancode.fp.support.PaymentCardRepository;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
public class MutantPipeline {
  //region .setField(
  private final PaymentCardRepository paymentCardRepository;
  //endregion

  //region .add
  public List<LocalDate> getShipDates(List<Order> orders) {
    List<LocalDate> shipDates = new ArrayList<>();
    /*List<LocalDate> shipDates = */
//    orders.stream()
//        .filter(Order::isActive)
//        .map(order -> order.shipDate().orElseGet(()->))
//        .forEach(order -> order.shipDate()
//            .ifPresent(e -> shipDates.add(e)));
    return shipDates;
  }

  //endregion
  private final PaymentCardMapper paymentCardMapper;

  //region +=
//  AtomicInteger
  public int totalActiveOrderPrice(List<Order> orders) {
    int sum = orders.stream()
        .filter(Order::isActive)
        .mapToInt(Order::price)
        .sum();
//        .forEach(order -> {
//          sum += order.price(); // side-effect in lambda care poate fi evitat
//        });
//        .reduce(0, (acc, x) -> acc + x); // reduce = FP kung-fu, de evitat in Java
    return sum;
  }
// PURE FUNCTION =
// - intoarce acelasi return pt aceiasi param
// - nu face side-effects: nu modifica date in jur sau sa dea network calls(I/O)
// ideal-> tot ce scrii dupa -> ar trebui sa fie pure function

  public PaymentCardDto updateCardAlias(long paymentCardId, long ssoId, String newAlias) {
    return paymentCardRepository.findById(paymentCardId)
        .filter(card -> card.getId() == ssoId)
        .map(card -> {
          card.setAlias(newAlias);
          return paymentCardMapper.toDto(paymentCardRepository.save(card));
        })
        .orElseThrow(() -> new IllegalArgumentException("Card " + paymentCardId + " with sso " + ssoId + " cannot be found"));
  }
  //endregion
}
