package victor.training.cleancode;

import java.util.List;

public class Guards {

  public static final int DEAD_PAY_AMOUNT = 1;

  public int getPayAmount(Marine marine, BonusPackage bonusPackage) {
    if (marine == null || (bonusPackage.value() < 10 || bonusPackage.value() > 100)) { // GUARD
      throw new IllegalArgumentException("Not applicable!"); // early throw
    }
    if (marine.dead()) {
      return DEAD_PAY_AMOUNT; // early return
    }
    if (marine.retired()) {
      return retiredAmount();
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
    return result;
  }

  private int retiredAmount() {
    return 2;
  }
}

record Marine(boolean dead, boolean retired, Integer yearsService, List<Award> awards) {
}
//There are only two things hard in programing:
//    cache invalidation and naming things

//xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx

record BonusPackage(int value) {
}

class Award {

}