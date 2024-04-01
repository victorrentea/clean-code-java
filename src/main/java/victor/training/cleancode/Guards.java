package victor.training.cleancode;

import java.util.List;

public class Guards {

  public static final int DEAD_PAY_AMOUNT = 1;

  public int getPayAmount(Marine marine, BonusPackage bonusPackage) {
    if (marine == null || (bonusPackage.value() <= 100 && bonusPackage.value() >= 10)) {
      throw new IllegalArgumentException("Not applicable!");
    }
    if (isDead(marine)) {
      return DEAD_PAY_AMOUNT;
    }
    if (marine.retired()) {
      return retiredAmount();
    }
    if (marine.yearsService() == null) {
      throw new IllegalArgumentException("Any marine should have the years of service set");
    }
    return calculatePayAmount(marine, bonusPackage);
  }

  private int calculatePayAmount(Marine marine, BonusPackage bonusPackage) {
    int result = marine.yearsService() * 100 + bonusPackage.value();
    if (!marine.awards().isEmpty()) {
      result += 1000;
    }
    if (marine.awards().size() >= 3) {
      result += 2000;
    }
    // HEAVY core logic here, business-rules ...
    return result;
  }

  private boolean isDead(Marine marine) {
    return false;
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