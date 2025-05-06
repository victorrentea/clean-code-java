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
  private final PaymentCardMapper paymentCardMapper;
  //endregion

  //region +=
  public int totalActiveOrderPrice(List<Order> orders) {
    int sum = 0;
    for (Order order : orders) {
      if (order.isActive()) {
        sum += order.price();
      }
    }
    return sum;
  }

  //region .add
  public List<LocalDate> getShipDates(List<Order> orders) {
    List<LocalDate> shipDates = new ArrayList<>();
    orders.stream()
        .filter(Order::isActive)
        .forEach(order -> order.shipDate().ifPresent(shipDates::add));
    return shipDates;
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
