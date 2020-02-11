package victor.training.refactoring;

import lombok.Data;

import java.util.Date;

public class EncapsulateConditionals {
    public double getQuote(Date date, RatesPlan plan, int quantity, float clientFidelityFactor) {
        double charge;
        if (!(date.before(plan.getSummerStart())) && date.before(plan.getSummerEnd()))
            charge = quantity * plan.getSummerRate();
        else
            charge = quantity * plan.getRegularRate() + plan.getRegularServiceCharge();
        return charge - clientFidelityFactor;
    }
}


@Data
class RatesPlan {
    private final Date summerStart;
    private final Date summerEnd;
    private final double summerRate;
    private final double regularRate;
    private final double regularServiceCharge;
}
