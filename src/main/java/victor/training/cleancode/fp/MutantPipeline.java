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
public class MutantPipeline {
  //region .setField(
  private final PaymentCardRepository paymentCardRepository;
  //endregion

  ////    List<LocalDate> list = getShipDates(List.of());
  ////    list.add(LocalDate.now()); // boom
//  }

//  public static void main(String[] args) {
//    new MutantPipeline(null, null).dusman();
//  }
//        .forEach(order -> order.shipDate().ifPresent(d -> shipDates.add(d)));

  //endregion
  private final PaymentCardMapper paymentCardMapper;

//  public void dusman() {

  //region .add
  public List<LocalDate> getShipDates(List<Order> orders) {
//    List<LocalDate> shipDates = orders.stream()
//        .filter(Order::isActive)
//        .map(Order::shipDate)
//        .filter(Optional::isPresent)
//        .map(Optional::get)
//        .collect(Collectors.toList());

    List<LocalDate> shipDates = orders.stream()
        .filter(Order::isActive)
        .map(Order::shipDate)
        .flatMap(Optional::stream)
//        .collect(toList());
        .toList();
    return shipDates;
  }

  //region +=
  public int totalActiveOrderPrice(List<Order> orders) {
    // DOAMNE FERESTE!
    //    var ref = new Object() {int sum = 0;};
    //     final int[] sum = {0};
    //    AtomicInteger sum = new AtomicInteger();
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
