package victor.training.refactoring;

import java.util.ArrayList;
import java.util.List;

public class GuardClauses {
    public int getPayAmount(Marine marine) {
        if (marine.isDead()) {
            return deadAmount();
        }
        if (marine.isRetired()) {
            return retiredAmount();
        }
        if (marine.getYearsService() == null) {
            throw new IllegalArgumentException("Any marine should have the years of servce set");
        }
        int result = marine.getYearsService() * 100;
//        if (!marine.getAwards().isEmpty()) {
        if (marine.hasAwards()) {
            result += 1000;
        }
        if (marine.getAwards().size() >= 3) {
            result += 2000;
        }
        return result;
    }

    private int deadAmount() {
        return 1;
    }

    private int retiredAmount() {
        return 2;
    }

    private int separatedAmount() {
        return 3;
    }

    private int normalPayAmount() {
        return 4;
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

    public Integer getYearsService() {
        return yearsService;
    }

    public List<Award> getAwards() {
        return awards;
    }

    public boolean isRetired() {
        return retired;
    }

    public boolean isDead() {
        return dead;
    }

    public boolean hasAwards() {
        return !awards.isEmpty();
    }
}

class Award {

}