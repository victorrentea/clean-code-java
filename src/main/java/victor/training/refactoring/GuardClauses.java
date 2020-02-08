package victor.training.refactoring;

public class GuardClauses {
    private boolean isDead = false;
    private boolean isSeparated = false;
    private boolean isRetired = false;

    public int getPayAmount()
    {
        int result;
        if (isDead)
            result = deadAmount();
        else {
            if (isSeparated)
                result = separatedAmount();
            else {
                if (isRetired)
                    result = retiredAmount();
                else
                    result = normalPayAmount();
            }
        }
        return result;
    }

    private int deadAmount()
    {
        return 1;
    }

    private int retiredAmount()
    {
        return 2;
    }

    private int separatedAmount()
    {
        return 3;
    }

    private int normalPayAmount()
    {
        return 4;
    }
}
