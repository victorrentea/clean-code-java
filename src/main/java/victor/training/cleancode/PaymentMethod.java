package victor.training.cleancode;

import java.util.Arrays;

public enum PaymentMethod {
    CARD,
  CASH,
  SANGE;

  public boolean isOneOf(PaymentMethod... option) {
    return Arrays.asList(option).contains(this);
  }
}
