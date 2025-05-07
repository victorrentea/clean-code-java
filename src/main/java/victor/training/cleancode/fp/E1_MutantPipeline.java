package victor.training.cleancode.fp;

import lombok.RequiredArgsConstructor;
import victor.training.cleancode.fp.support.Order;
import victor.training.cleancode.fp.support.PaymentCardDto;
import victor.training.cleancode.fp.support.PaymentCardMapper;
import victor.training.cleancode.fp.support.PaymentCardRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
public class E1_MutantPipeline {
  //region +=
  public int totalActiveOrderPrice(List<Order> orders) {
    return orders.stream()
        .filter(Order::isActive)
        .mapToInt(Order::price)
        .sum();
  }
  //endregion

  //region .add
  public List<LocalDate> getShipDates(List<Order> orders) {
    return orders.stream()
        .filter(Order::isActive)
        .map(Order::shipDate)
        .flatMap(Optional::stream)
        .toList();
  }
  //endregion

  //region .setField(
  private final PaymentCardRepository paymentCardRepository;
  private final PaymentCardMapper paymentCardMapper;

  public PaymentCardDto updateCardAlias(long paymentCardId, long ssoId, String newAlias) {
    var paymentCard = paymentCardRepository.findById(paymentCardId)
        .filter(card -> card.getId() == ssoId)
        .orElseThrow();
    paymentCard.setAlias(newAlias);
    var saved = paymentCardRepository.save(paymentCard);
    return paymentCardMapper.toDto(saved);
  }
  //endregion
}
