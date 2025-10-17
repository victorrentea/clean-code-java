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
    List<LocalDate> shipDates = orders.stream()
        .filter(Order::isActive)
//        .forEach(order -> order.shipDate().ifPresent(shipDates::add));
        .flatMap(order -> order.shipDate().stream())
        .toList();
    return shipDates;
  }
  //endregion
  private final PaymentCardMapper paymentCardMapper;

  //region +=
  public int totalActiveOrderPrice(List<Order> orders) {
    return orders.stream()
        .filter(Order::isActive)
        .mapToInt(Order::price)
        .sum();
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
