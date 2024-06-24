package victor.training.cleancode;

import lombok.Value;

import java.util.List;

public class Guards {

  public static final int DEAD_PAY_AMOUNT = 1;

  public int getPayAmount(Marine marine, BonusPackage bonusPackage) {
    int r;
    if (marine != null && !(bonusPackage.value() < 10 || bonusPackage.value() > 100)) {
      if (!marine.dead()) {
        if (!marine.retired()) {
          if (marine.yearsService() != null) {
            r = marine.yearsService() * 100 + bonusPackage.value();
            if (!marine.awards().isEmpty()) {
              r += 1000;

            }
            if (marine.awards().size() >= 3) {
              r += 2000;
            }
            // HEAVY core logic here, business-rules ...
          } else {
            throw new IllegalArgumentException("Any marine should have the years of service set");
          }
        } else {
          r = retiredAmount();
        }
      } else {
        r = DEAD_PAY_AMOUNT;
      }
    } else{
      throw new IllegalArgumentException("Not applicable!");
    }
    return r; // TODO ALT-ENTER move return closer
  }

  private int retiredAmount() {
    return 2;
  }

}

record Marine(boolean dead, boolean retired, Integer yearsService, List<Award> awards) {
}

record BonusPackage(int value) {
}

class Award {

}