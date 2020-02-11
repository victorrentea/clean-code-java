package victor.training.refactoring;

public class GuardClauses {
    private boolean isDead = false;
    private boolean isSeparated = false;
    private boolean isRetired = false;

    public int getPayAmount() {
        if (isDead)
            return deadAmount();
        if (isSeparated)
            return separatedAmount();
        if (isRetired)
            return retiredAmount();

        // 50 linii de logica grea
        return normalPayAmount();
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
