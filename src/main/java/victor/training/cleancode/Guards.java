package victor.training.cleancode;

import java.util.List;

public class Guards {

  public static final int DEAD_PAY_AMOUNT = 1;

  public int getPayAmount(Marine marine, BonusPackage bonusPackage) {
    int result;
    if (marine != null && !(bonusPackage.value() < 10 || bonusPackage.value() > 100)) {
      if (!marine.dead()) {
        if (!marine.retired()) {
          if (marine.yearsService() != null) {
            result = marine.yearsService() * 100 + bonusPackage.value();
            if (!marine.awards().isEmpty()) {
              result += 1000;
            }
            if (marine.awards().size() >= 3) {
              result += 2000;
            }
          } else {
            throw new IllegalArgumentException("Any marine should have the years of service set");
          }
        } else {
          result = retiredAmount();
        }
      } else {
        result = DEAD_PAY_AMOUNT;
      }
    } else {
      throw new IllegalArgumentException("Not applicable!");
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