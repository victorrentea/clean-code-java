package victor.training.cleancode;

import java.util.List;

public class Guards {

  public static final int DEAD_PAY_AMOUNT = 1;


  public int getPayAmount(Marine marine, BonusPackage bonusPackage) {
    if (marine == null || (bonusPackage.value() <= 100 && bonusPackage.value() >= 10)) {
      throw new IllegalArgumentException("Not applicable!"); // fail fast
    }
    if (isDead(marine)) {
      return DEAD_PAY_AMOUNT;
    }
    if (marine.retired()) {// guard condition
      return retiredAmount(); // early return
    }
    if (marine.yearsService() == null) {
      throw new IllegalArgumentException("Any marine should have the years of service set");
    }
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

  // too explicit productName; spelling out the body> names should tell WHAT not HOW
//  private boolean marineNotNullAndBonusIsRight(Marine marine, BonusPackage bonusPackage) {
  private boolean isEligibleForBonus(Marine marine, BonusPackage bonusPackage) {// not enough
    return marine != null && (bonusPackage.value() > 100 || bonusPackage.value() < 10);
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