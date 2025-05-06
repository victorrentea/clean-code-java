package victor.training.cleancode.fp.support;

import java.util.Optional;

public interface PaymentCardRepository {
  Optional<PaymentCard> findById(long paymentCardId);

  PaymentCard save(PaymentCard card);
}