package victor.training.cleancode.support;

public class PaymentCardMapper {
  public PaymentCardDto toDto(PaymentCard card) {
    return new PaymentCardDto(card.getId(), card.getAlias());
  }
}