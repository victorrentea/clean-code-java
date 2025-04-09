package victor.training.cleancode;

import java.util.List;

public class Guards {

  public static final int DEAD_PAY_AMOUNT = 1;

  public int getPayAmount(Marine marine, BonusPackage bonusPackage) {
    //  if (!( marine != null && !(bonusPackage.value() < 10 || bonusPackage.value() > 100))) {
    if (marine == null || bonusPackage.value() < 10 || bonusPackage.value() > 100) {
      throw new IllegalArgumentException("Not applicable!");
    }
    if (marine.dead()) {
      return DEAD_PAY_AMOUNT;
    }
    if (marine.retired()) {
      return retiredAmount();
    }
    if (marine.yearsOfService() == null) {
      throw new IllegalArgumentException("Any marine should have the years of service set");
    }
    int result = marine.yearsOfService() * 100 + bonusPackage.value();
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

// Codul pe care lucrezi astazi a fost inceput de echipa mea?
// Cat de curat estimezi ca iti este codul din proiect A=jale, E=impecabil

record Marine(boolean dead, boolean retired, Integer yearsOfService, List<Award> awards) {}

record BonusPackage(int value) {
}

class Award {

}