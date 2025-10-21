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
    orders.stream()
        .filter(Order::isActive)
        .forEach(order -> order.shipDate().ifPresent(shipDates::add));
    return shipDates;
  }
  //endregion


  private final PaymentCardMapper paymentCardMapper;

  public int totalActiveOrderPrice(List<Order> orders) {
    int sum = orders.stream().filter(Order::isActive).mapToInt(Order::price).sum();
//    for (Order order : orders) {
//      if (order.isActive()) {
//        sum += order.price();
//      }
//    }
    // ts: .reduce(0,(acc,o)=>acc+o.price))
    // py: sum= sum(o.price for o in orders if o.active)
    return sum;
  }


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
