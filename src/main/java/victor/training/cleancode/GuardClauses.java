package victor.training.cleancode;

import lombok.Data;
import lombok.Value;

import java.util.ArrayList;
import java.util.List;

public class GuardClauses {

  public static final int DEAD_PAY_AMOUNT = 1;

  public int getPayAmount(Marine marine, BonusPackage bonusPackage) {
    int result;
    if (marine != null && (bonusPackage.getValue() > 100 || bonusPackage.getValue() < 10)) {
      if (!isDead(marine)) {
        if (!marine.isRetired()) {
          if (marine.getYearsService() != null) {
            result = marine.getYearsService() * 100 + bonusPackage.getValue();
            if (!marine.getAwards().isEmpty()) {
              result += 1000;
            }
            if (marine.getAwards().size() >= 3) {
              result += 2000;
            }
            // HEAVY core logic here, business-rules ...
          } else {
            throw new IllegalArgumentException("Any marine should have the years of service set");
          }
        } else result = retiredAmount();
      } else {
        result = DEAD_PAY_AMOUNT;
      }
    } else{
      throw new IllegalArgumentException("Not applicable!");
    }
    return result; // TODO ALT-ENTER move return closer
  }

  private boolean isDead(Marine marine) {
    return false;
  }

  private int retiredAmount() {
    return 2;
  }

}

@Value
class Marine {
  boolean dead;
  boolean retired;
  Integer yearsService;
  List<Award> awards = new ArrayList<>();

}

@Data
class BonusPackage {
  int value;
}

class Award {

}