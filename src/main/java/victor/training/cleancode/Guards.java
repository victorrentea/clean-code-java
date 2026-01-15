package victor.training.cleancode;

import java.util.List;

public class Guards {

  public static final int DEAD_PAY_AMOUNT = 1;

  public int getPayAmount(Marine marine, BonusPackage bonusPackage) {
    int result = 0;

    return result;
  }

  private int retiredAmount() {
    return 2;
  }
}

record Marine(boolean dead, boolean retired, Integer yearsOfService, List<Award> awards) {
}

record BonusPackage(int value) {
}

class Award {

}