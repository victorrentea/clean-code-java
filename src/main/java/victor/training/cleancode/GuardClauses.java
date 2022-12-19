package victor.training.cleancode;

import java.util.ArrayList;
import java.util.List;

public class GuardClauses {

  public static final int DEAD_PAY_AMOUNT = 1;

  public int getPayAmount(Marine marine) {
    if (marine == null) {
      throw new RuntimeException("Marine is null");
    }
    if (isDead(marine)) {
      return DEAD_PAY_AMOUNT;
    }
    if (marine.isRetired()) {
      return retiredAmount();
    }
    if (marine.getYearsService() == null) {
      throw new IllegalArgumentException("Any marine should have the years of service set");
    }
    int result = marine.getYearsService() * 100;
    if (!marine.getAwards().isEmpty()) {
      result += 1000;
    }
    if (marine.getAwards().size() >= 3) {
      result += 2000;
    }
    // HEAVY logic here...
    return result;
  }

  private boolean isDead(Marine marine) {
    return false;
  }

  private int retiredAmount() {
    return 2;
  }

}

class Marine {
  private final boolean dead;
  private final boolean retired;
  private final Integer yearsService;
  private final List<Award> awards = new ArrayList<>();

  Marine(boolean dead, boolean retired, Integer yearsService) {
    this.dead = dead;
    this.retired = retired;
    this.yearsService = yearsService;
  }

  public List<Award> getAwards() {
    return awards;
  }

  public Integer getYearsService() {
    return yearsService;
  }

  public boolean isRetired() {
    return retired;
  }

  public boolean isDead() {
    return dead;
  }
}

class Award {

}