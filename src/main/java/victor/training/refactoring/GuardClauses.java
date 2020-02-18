package victor.training.refactoring;

import lombok.Data;

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
        if (!marine.getAwards().isEmpty()) {
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

@Data
class Marine {
    private final boolean dead;
    private final boolean retired;
    private final Integer yearsService;
    private final List<Award> awards = new ArrayList<>();
}

class Award {

}